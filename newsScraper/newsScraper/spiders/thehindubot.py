# -*- coding: utf-8 -*-
import scrapy


class ThehindubotSpider(scrapy.Spider):
    name = 'thehindubot'
    allowed_domains = ["www.thehindu.com"]
    start_urls = ["https://www.thehindu.com/archive/"]

    def parse(self, response):
        for href in response.xpath('//*[@id="archiveWebContainer"]/div[2]/ul/li/a/@href'):
            url = response.urljoin(href.extract())
            yield scrapy.Request(url, callback = self.parse_month)

    def parse_month(self, response):
        for href in response.xpath('//*[@id="archiveDayDatePicker"]/table/tbody/tr/td/a/@href'):
            url = response.urljoin(href.extract())
            yield scrapy.Request(url, callback = self.parse_day)

    def parse_day(self, response):
        for href in response.xpath('//section/div[2]/div/div/div/ul/li/a/@href'):
            url = response.urljoin(href.extract())
            yield scrapy.Request(url, callback = self.parse_article)

    def parse_article(self, response):
        desc = ''
        for node in response.xpath('/html/body/div[2]/div[2]/div/div/div/section/div/div/div/div[3]/div//p/text()'):
            desc = desc + ' ' + node.get()
        #yield desc
        scraped_info = {
            'title' : response.xpath('/html/body/div[2]/div[2]/div/div/div/section/div/div/div/div[1]/h1/text()').get().replace('\n', ''),
            'url' : response.url,
            'author' : response.css(".auth-nm::text").get().replace('\n', ''),
            'description': desc
        }
        yield scraped_info
