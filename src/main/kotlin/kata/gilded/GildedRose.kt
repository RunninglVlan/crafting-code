package kata.gilded

const val LEGENDARY_ITEM = "Sulfuras, Hand of Ragnaros"
const val AGED_BRIE = "Aged Brie"

class GildedRose(var items: Array<Item>) {

	fun updateQuality() {
		items.forEach {
			if (it.name != AGED_BRIE && it.name != "Backstage passes to a TAFKAL80ETC concert") {
				if (it.quality > 0) {
					if (it.name != LEGENDARY_ITEM) {
						it.quality--
					}
				}
			} else {
				if (it.quality < 50) {
					it.quality++

					if (it.name == "Backstage passes to a TAFKAL80ETC concert") {
						if (it.sellIn < 11) {
							if (it.quality < 50) {
								it.quality++
							}
						}

						if (it.sellIn < 6) {
							if (it.quality < 50) {
								it.quality++
							}
						}
					}
				}
			}

			if (it.name != LEGENDARY_ITEM) {
				it.sellIn--
			}

			if (it.sellIn < 0) {
				if (it.name != AGED_BRIE) {
					if (it.name != "Backstage passes to a TAFKAL80ETC concert") {
						if (it.quality > 0) {
							if (it.name != LEGENDARY_ITEM) {
								it.quality--
							}
						}
					} else {
						it.quality = 0
					}
				} else {
					if (it.quality < 50) {
						it.quality++
					}
				}
			}
		}
	}
}
