package kata.gilded

import spock.lang.Specification

/**
 * Requirements: <a href="https://github.com/testdouble/contributing-tests/wiki/Gilded-Rose-Kata">Gilded Rose</a>
 */
class GildedRoseShould extends Specification {

	def 'lower sellIn (days) and quality for every item each day'() {
		given:
		def inn = innWith(item(5, 3), item(10, 7))

		when:
		inn.updateQuality()

		then:
		inn.items.collect { it.sellIn } == [4, 9]
		inn.items.collect { it.quality } == [2, 6]

		when:
		inn.updateQuality()

		then:
		inn.items.collect { it.sellIn } == [3, 8]
		inn.items.collect { it.quality } == [1, 5]
	}

	def 'degrade quality twice as fast once the sell by date has passed'() {
		given:
		def inn = innWith(item(2, 10))

		when:
		2.times { inn.updateQuality() }

		then:
		inn.items.first().quality == 8

		when:
		inn.updateQuality()

		then:
		inn.items.first().quality == 6
	}

	def "quality can't be negative"() {
		given:
		def inn = innWith(item(1, 1))

		when:
		2.times { inn.updateQuality() }

		then:
		inn.items.first().sellIn == -1
		inn.items.first().quality == 0
	}

	def 'quality of Aged Brie increases the older it gets'() {
		given:
		def inn = innWith(new Item('Aged Brie', 2, 2))

		when:
		inn.updateQuality()

		then:
		inn.items.first().sellIn == 1
		inn.items.first().quality == 3
	}

	def 'max quality is 50'() {
		given:
		def inn = innWith(new Item('Aged Brie', 2, 49))

		when:
		2.times { inn.updateQuality() }

		then:
		inn.items.first().sellIn == 0
		inn.items.first().quality == 50
	}

	def 'Sulfuras never has to be sold or decreases in quality'() {
		given:
		def inn = innWith(new Item('Sulfuras, Hand of Ragnaros', 10, 80))

		when:
		2.times { inn.updateQuality() }

		then:
		inn.items.first().sellIn == 10
		inn.items.first().quality == 80
	}

	def 'Backstage passes increase in quality'() {
		given:
		def inn = innWith(
				new Item('Backstage passes to a TAFKAL80ETC concert', 20, 10),
				new Item('Backstage passes to a TAFKAL70ETC concert', 10, 10),
				new Item('Backstage passes to a TAFKAL60ETC concert', 5, 10),
				new Item('Backstage passes to a TAFKAL50ETC concert', 0, 10)
		)

		when:
		inn.updateQuality()

		then:
		inn.items.collect { it.quality } == [11, 12, 13, 0]
	}

	def 'Conjured items degrade in quality twice as fast as normal items'() {
		given:
		def inn = innWith(
				new Item('Conjured Mana Cake', 5, 10),
				new Item('Conjured Mana Potion', -5, 10),
		)

		when:
		inn.updateQuality()

		then:
		inn.items.collect { it.quality } == [8, 6]
	}

	static innWith(Item... items) {
		new GildedRose(items)
	}

	static item(sellInDays, quality) {
		new Item('item' + Math.random(), sellInDays, quality)
	}
}
