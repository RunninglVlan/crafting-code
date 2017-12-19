package kata

class ArabicNumeral(private val int: Int) {

	companion object {
		private val BASE_NUMERALS: Map<Int, String> = mapOf(
				Pair(1, "I"),
				Pair(5, "V"),
				Pair(10, "X"),
				Pair(50, "L"),
				Pair(100, "C"),
				Pair(500, "D"),
				Pair(1000, "M")
		)

		private val EXCEPTIONAL_NUMERALS: Map<Int, String> = mapOf(
				Pair(4, "IV"),
				Pair(9, "IX"),
				Pair(40, "XL"),
				Pair(90, "XC"),
				Pair(400, "CD"),
				Pair(900, "CM")
		)

		private val BASE_AND_EXCEPTIONAL_NUMERALS = BASE_NUMERALS.plus(EXCEPTIONAL_NUMERALS)
				.entries.sortedByDescending { it.key }
	}

	fun toRomanNumeral(): String {
		validate()
		return toRomanNumeral(int)
	}

	private fun validate() {
		if (int < 1 || int > 3999) {
			throw IllegalArgumentException()
		}
	}

	private fun toRomanNumeral(int: Int): String {
		var (arabic, roman) = closestNumeral(int)
		if (arabic < int) {
			roman += toRomanNumeral(int - arabic)
		}
		return roman
	}

	private fun closestNumeral(int: Int): Map.Entry<Int, String> {
		return BASE_AND_EXCEPTIONAL_NUMERALS.first { it.key <= int }
	}
}
