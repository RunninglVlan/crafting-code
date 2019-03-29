package kata.gilded

class GildedRose(var items: Array<Item>) {

	fun updateQuality() {
		for (i in items.indices) {
			if (!items[i].name.equals("Aged Brie") && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
				if (items[i].quality > 0) {
					if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
						items[i].quality = items[i].quality - 1
					}
				}
			} else {
				if (items[i].quality < 50) {
					items[i].quality = items[i].quality + 1

					if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
						if (items[i].sellIn < 11) {
							if (items[i].quality < 50) {
								items[i].quality = items[i].quality + 1
							}
						}

						if (items[i].sellIn < 6) {
							if (items[i].quality < 50) {
								items[i].quality = items[i].quality + 1
							}
						}
					}
				}
			}

			if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
				items[i].sellIn = items[i].sellIn - 1
			}

			if (items[i].sellIn < 0) {
				if (!items[i].name.equals("Aged Brie")) {
					if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
						if (items[i].quality > 0) {
							if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
								items[i].quality = items[i].quality - 1
							}
						}
					} else {
						items[i].quality = items[i].quality - items[i].quality
					}
				} else {
					if (items[i].quality < 50) {
						items[i].quality = items[i].quality + 1
					}
				}
			}
		}
	}
}

fun main(args: Array<String>) {

	println("OMGHAI!")

	val items = arrayOf(Item("+5 Dexterity Vest", 10, 20), //
			Item("Aged Brie", 2, 0), //
			Item("Elixir of the Mongoose", 5, 7), //
			Item("Sulfuras, Hand of Ragnaros", 0, 80), //
			Item("Sulfuras, Hand of Ragnaros", -1, 80),
			Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
			Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
			Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
			// this conjured item does not work properly yet
			Item("Conjured Mana Cake", 3, 6))

	val app = GildedRose(items)

	var days = 2
	if (args.size > 0) {
		days = Integer.parseInt(args[0]) + 1
	}

	for (i in 0..days - 1) {
		println("-------- day $i --------")
		println("name, sellIn, quality")
		for (item in items) {
			println(item)
		}
		println()
		app.updateQuality()
	}
}
