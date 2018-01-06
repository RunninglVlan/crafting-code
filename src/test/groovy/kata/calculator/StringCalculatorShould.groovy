package kata.calculator

import spock.lang.Specification

/**
 * Requirements:
 * <a href="http://osherove.com/tdd-kata-1">String Calculator</a>,
 * <a href="http://osherove.com/tdd-kata-2">String Calculator with Dependencies</a>
 */
class StringCalculatorShould extends Specification {

	def logger = Mock(Logger)
	def webService = Mock(WebService)

	def calculator = new StringCalculator(logger, webService)

	def 'add 0, 1 or 2 numbers'() {
		expect:
		calculator.add(numbers) == sum

		where:
		numbers | sum
		''      | 0
		'2'     | 2
		'4,5'   | 9
	}

	def 'add more than 2 numbers'() {
		expect:
		calculator.add(numbers) == sum

		where:
		numbers     | sum
		'4,5,6'     | 15
		'5,6,7,8'   | 26
		'2,3,4,5,6' | 20
	}

	def 'support new line delimiter in addition to default comma delimiter'() {
		expect:
		calculator.add(numbers) == sum

		where:
		numbers  | sum
		'12\n3'  | 15
		'1\n2,3' | 6
	}

	def 'support other delimiters of one character'() {
		expect:
		calculator.add(numbers) == sum

		where:
		numbers     | sum
		'//;\n3;6'  | 9
		'//k\n1k2'  | 3
		'//5\n256'  | 8
		'//[\n10[4' | 14
		'//]\n8]1'  | 9
		'//.\n3.9'  | 12
	}

	def 'support other delimiters of multiple characters'() {
		expect:
		calculator.add(numbers) == sum

		where:
		numbers              | sum
		'//[kk]\n2kk3'       | 5
		'//[;5]\n1;512'      | 13
		'//[***]\n1***2***3' | 6
		'//[[[]\n8[[7[[6'    | 21
		'//[]]\n8]9]10'      | 27
		'//[.]\n1.4.7.12'    | 24
	}

	def 'use default comma delimiter if incorrect delimiter format is used'() {
		expect:
		calculator.add(numbers) == sum

		where:
		numbers     | sum
		'//[]\n1,3' | 4
		'//\n2,9'   | 11
	}

	def 'support multiple delimiters'() {
		expect:
		calculator.add(numbers) == sum

		where:
		numbers                | sum
		'//[*][%]\n1*2%3'      | 6
		'//[kk][^&]\n2kk3^&44' | 49
	}

	def 'not support negative numbers'() {
		when:
		calculator.add(numbers)

		then:
		def exception = thrown(IllegalStateException)
		exception.message == "Negatives not allowed: $negatives"

		where:
		numbers | negatives
		'-3,6'  | '-3'
		'2,-1'  | '-1'
		'-2,-1' | '-2, -1'
	}

	def 'ignore numbers larger than 1000'() {
		expect:
		calculator.add(numbers) == sum

		where:
		numbers      | sum
		'5,4,1001'   | 9
		'6,3,1,2000' | 10
		'1,5,1000'   | 1006
	}

	def 'log result'() {
		when:
		calculator.add(numbers)

		then:
		1 * logger.write(sum)

		where:
		numbers   | sum
		''        | 0
		'2'       | 2
		'4,5'     | 9
		'5,6,7,8' | 26
	}

	def 'notify web service when logging fails'() {
		given:
		logger.write(_) >> { throw new RuntimeException(message) }

		when:
		calculator.add('')

		then:
		1 * webService.error(message)

		where:
		message << ['Unexpected error', 'Unexpected problem']
	}
}
