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

			if (it.name != AGED_BRIE && !it.name.contains(BACKSTAGE_PASSES)) {
				it.quality--
			} else {
				it.quality++

				if (it.name.contains(BACKSTAGE_PASSES)) {
					if (it.sellIn < 11) {
						it.quality++
					}

					if (it.sellIn < 6) {
						it.quality++
					}
				}
			}

			it.sellIn--

			if (it.sellIn < 0) {
				if (it.name != AGED_BRIE) {
					if (!it.name.contains(BACKSTAGE_PASSES)) {
						it.quality--
					} else {
						it.quality = 0
					}
				} else {
					it.quality++
				}
			}
			it.quality = Math.max(it.quality, 0)
			it.quality = Math.min(it.quality, 50)
		}
	}
}
