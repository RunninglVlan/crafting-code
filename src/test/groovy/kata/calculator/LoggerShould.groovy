package kata.calculator

import spock.lang.Specification

class LoggerShould extends Specification {

	def stream = Mock(PrintStream, constructorArgs: [Stub(OutputStream)])

	def logger = new Logger()

	def setup() {
		System.setOut(stream)
	}

	def 'write number to console'() {
		when:
		logger.write(number)

		then:
		1 * stream.println(number)

		where:
		number << [1, 20, 30]
	}

	def 'write numbers to console'() {
		when:
		logger.write(5)
		logger.write(10)

		then:
		2 * stream.println(_ as Integer)
	}
}
