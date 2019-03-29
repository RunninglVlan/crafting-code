package kata.gilded

const val LEGENDARY_ITEM = "Sulfuras, Hand of Ragnaros"
const val AGED_BRIE = "Aged Brie"
const val BACKSTAGE_PASSES = "Backstage passes"

class GildedRose(var items: Array<Item>) {

	fun updateQuality() {
		items.forEach {
			if (it.name == LEGENDARY_ITEM) {
				return@forEach
			}

			it.sellIn--

			if (it.name == AGED_BRIE) {
				if (it.sellIn < 0) {
					it.quality += 2
				} else {
					it.quality++
				}
			} else if (it.name.contains(BACKSTAGE_PASSES)) {
				when {
					it.sellIn < 0 -> it.quality = 0
					it.sellIn < 5 -> it.quality += 3
					it.sellIn < 10 -> it.quality += 2
					else -> it.quality++
				}
			} else {
				if (it.sellIn < 0) {
					it.quality -= 2
				} else {
					it.quality--
				}
			}

			it.quality = Math.max(it.quality, 0)
			it.quality = Math.min(it.quality, 50)
		}
	}
}
