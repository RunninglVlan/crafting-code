package kata

import spock.lang.Specification

/**
 * Description: <a href="https://en.wikipedia.org/wiki/Roman_numerals">Roman numerals</a>
 */
@SuppressWarnings("GroovyAssignabilityCheck")
class ArabicNumeralShould extends Specification {

	def 'return base roman numerals'() {
		expect:
		new ArabicNumeral(arabic).toRomanNumeral() == roman

		where:
		arabic | roman
		1      | 'I'
		5      | 'V'
		10     | 'X'
		50     | 'L'
		100    | 'C'
		500    | 'D'
		1000   | 'M'
	}

	def 'return exceptional roman numerals'() {
		expect:
		new ArabicNumeral(arabic).toRomanNumeral() == roman

		where:
		arabic | roman
		4      | 'IV'
		9      | 'IX'
		40     | 'XL'
		90     | 'XC'
		400    | 'CD'
		900    | 'CM'
	}

	def 'add different suffixes to base roman numerals'() {
		expect:
		new ArabicNumeral(arabic).toRomanNumeral() == roman

		where:
		arabic | roman
		2      | 'II'
		8      | 'VIII'
		11     | 'XI'
		60     | 'LX'
		120    | 'CXX'
		530    | 'DXXX'
		600    | 'DC'
		800    | 'DCCC'
		1200   | 'MCC'
		2000   | 'MM'
		3000   | 'MMM'
	}

	def 'throw exception if arabic numeral is out of range of supported conversions'() {
		when:
		new ArabicNumeral(arabic).toRomanNumeral()

		then:
		thrown(IllegalArgumentException)

		where:
		arabic << [0, 4000]
	}

	def 'return complex roman numerals'() {
		expect:
		new ArabicNumeral(arabic).toRomanNumeral() == roman

		where:
		arabic | roman
		398    | 'CCCXCVIII'
		629    | 'DCXXIX'
		1681   | 'MDCLXXXI'
		1704   | 'MDCCIV'
		3732   | 'MMMDCCXXXII'
	}
}
