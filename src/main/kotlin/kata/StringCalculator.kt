package kata

object StringCalculator {

	@JvmStatic
	fun add(numbers: String): Int {
		val intNumbers = StringNumbers(numbers).value()
				.map(this::toInt)
				.filter { it <= 1000 }
		ensureNoNegatives(intNumbers)
		return intNumbers.sum()
	}

	private fun toInt(number: String) = number.toIntOrNull() ?: 0

	private fun ensureNoNegatives(numbers: List<Int>) {
		val negatives = numbers.filter { it < 0 }
		check(negatives.isEmpty()) {
			"Negatives not allowed: ${negatives.joinToString()}"
		}
	}

	private class StringNumbers(private val numbers: String) {

		companion object {
			private val DELIMITER_PARAMETER: String = "//"
			private val NEW_LINE_DELIMITER: String = "\n"
			private val DEFAULT_DELIMITER: String = ","
			private val DELIMITER_WRAPPERS: List<String> = listOf("[", "]")
			private val DELIMITER_OF_WRAPPED_DELIMITERS: String = "]["
		}

		private var delimiters: Array<String> =
				arrayOf(DEFAULT_DELIMITER, NEW_LINE_DELIMITER)
		private var stringNumbers: String = numbers

		init {
			if (delimiterIsPassed()) {
				stringNumbers = numbers.substringAfter(NEW_LINE_DELIMITER)

				val delimiter = numbers.substringAfter(DELIMITER_PARAMETER)
						.substringBefore(NEW_LINE_DELIMITER)
				if (isSimple(delimiter)) {
					delimiters = arrayOf(delimiter)
				} else if (isWrapped(delimiter)) {
					delimiters = wrappedDelimiters(delimiter)
				}
			}
		}

		private fun delimiterIsPassed(): Boolean {
			return numbers.startsWith(DELIMITER_PARAMETER) &&
					numbers.contains(NEW_LINE_DELIMITER)
		}

		private fun isSimple(delimiter: String): Boolean = delimiter.length == 1

		private fun isWrapped(delimiter: String): Boolean {
			return delimiter.length > 2 &&
					delimiter.startsWith(DELIMITER_WRAPPERS.first()) &&
					delimiter.endsWith(DELIMITER_WRAPPERS.last())
		}

		private fun wrappedDelimiters(delimiter: String): Array<String> {
			val wrappedDelimiter = delimiter
					.substringAfter(DELIMITER_WRAPPERS.first())
					.substringBeforeLast(DELIMITER_WRAPPERS.last())
			return if (isSingle(wrappedDelimiter)) {
				arrayOf(wrappedDelimiter)
			} else {
				wrappedDelimiter.split(DELIMITER_OF_WRAPPED_DELIMITERS)
						.toTypedArray()
			}
		}

		private fun isSingle(wrappedDelimiter: String): Boolean {
			return !wrappedDelimiter.contains(DELIMITER_OF_WRAPPED_DELIMITERS)
		}

		fun value(): List<String> = stringNumbers.split(*delimiters)
	}
}
