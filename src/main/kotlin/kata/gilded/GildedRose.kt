package kata.gilded

private const val LEGENDARY_ITEM = "Sulfuras, Hand of Ragnaros"
private const val AGED_BRIE = "Aged Brie"
private const val BACKSTAGE_PASSES = "Backstage passes"

class GildedRose(var items: Array<Item>) {

	fun updateQuality() {
		items.forEach { item ->
			updateQualityOf(item)
		}
	}

	private fun updateQualityOf(item: Item) {
		with(item.name) {
			when {
				equals(LEGENDARY_ITEM) -> doNotChangeLegendaryItem()
				equals(AGED_BRIE) -> increaseQualityOf(item)
				startsWith(BACKSTAGE_PASSES) -> updateQualityOfBackstage(item)
				else -> degradeQualityOfRegular(item)
			}
		}
	}

	private fun doNotChangeLegendaryItem() {
		return
	}

	private fun increaseQualityOf(item: Item) {
		changeItemQualityWithSign(item, 1)
	}

	private fun updateQualityOfBackstage(pass: Item) {
		pass.sellIn--
		when {
			pass.sellIn < 0 -> pass.quality = 0
			pass.sellIn < 5 -> pass.quality += 3
			pass.sellIn < 10 -> pass.quality += 2
			else -> pass.quality++
		}
		pass.quality = Math.min(pass.quality, 50)
	}

	private fun degradeQualityOfRegular(item: Item) {
		changeItemQualityWithSign(item, -1)
	}

	private fun changeItemQualityWithSign(item: Item, sign: Int) {
		item.sellIn--
		if (item.sellIn < 0) {
			item.quality += sign * 2
		} else {
			item.quality += sign
		}
		item.quality = Math.min(item.quality, 50)
		item.quality = Math.max(item.quality, 0)
	}
}
