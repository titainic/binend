package com.titanic.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.titanic.bean.ItemBean;

/**
 * rss解析
 * @author titanic
 *
 */
public class RSSUtils
{

	//如果抓取的html片段中,没有jpg图片.设置为默认此连接的图片
	private static String defaultImageUrl = "http://cdn.duitang.com/uploads/item/201402/15/20140215233020_MKCKn.thumb.700_0.jpeg";

	/**
	 * 活取页面里面的图片,和listview里面的详细代码html片段
	 * 
	 * @param urla
	 * @param flag
	 *            值为1时获取(ithome)html代码片段
	 * @return
	 */
	public static Map<String, String> getCenter(String urla, int flag)
	{
		Map<String, String> map = new HashMap<String, String>();
		Element jpg  = null;
		Document doc = null;
		if (flag == 1)
		{
			try
			{
				URL centurl = new URL(urla);
				doc = Jsoup.parse(centurl, 3000);
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			Element element = doc.getElementById("paragraph");
			String center = element.toString();
			map.put("center", center);
			 jpg = doc.select("img[src$=.jpg]").first();
		}
		
		if(flag ==2)
		{
			doc = Jsoup.parse(urla);
			 jpg = doc.select("img[src$=.jpg]").first();
			 if(jpg == null)
			 {
				 jpg = doc.select("img[src$=.png]").first();
				 if(jpg == null)
				 {
					 jpg = doc.select("img[src$=.jepg]").first();
				 }
			 }
		}


		if (jpg == null || jpg.attr("src") == null)
		{
			map.put("jpg", defaultImageUrl);
		} else
		{
			String img = jpg.attr("src");
			map.put("jpg", img);
		}

		return map;
	}

	/**
	 * 请求rss,并解析,获取返回结果 ITHOME
	 * 
	 * @return
	 */
	public static List<ItemBean> parseRss(String rssUrl, int flag)
	{
		List<ItemBean> list = new ArrayList<ItemBean>();
		String rss = rssUrl;
		try
		{
			URL url = new URL(rss);
			// 读取Rss源
			XmlReader reader = new XmlReader(url);

			System.out.println("Rss源的编码格式为：" + reader.getEncoding());
			SyndFeedInput input = new SyndFeedInput();
			// 得到SyndFeed对象，即得到Rss源里的所有信息
			SyndFeed feed = input.build(reader);


			// 得到Rss新闻中子项列表
			List entries = feed.getEntries();

			// 循环得到每个子项信息
			for (int i = 0; i < entries.size(); i++)
			{
				SyndEntry entry = (SyndEntry) entries.get(i);
				ItemBean item = new ItemBean();

				// 标题、连接地址、标题简介、时间是一个Rss源项最基本的组成部分
				System.out.println("标题：" + entry.getTitle());
				item.setTitle(entry.getTitle());

				System.out.println("连接地址：" + entry.getLink());
				item.setLink(entry.getLink());
				
				Map<String, String> map = null;
				
				SyndContent description = entry.getDescription();
				
				// 标志为1是获取html片段.显示的内容
				if (flag == 1)
				{
					// 获取连接中的图片连接
					map = getCenter(entry.getLink(), flag);
					item.setCenter(map.get("center"));

					System.out.println("发布时间：" + entry.getPublishedDate());
					String data = DataUtils.cstTime2String(entry.getPublishedDate() + "");
					item.setPubDate(data);
				}
				else
				{
					map = getCenter(description.getValue(), flag);
				}

				//获取html片段中,抓取 的jpg图片
				item.setImgUrl(map.get("jpg"));
				System.out.println("标题简介：" + description.getValue());
				item.setCdata(description.getValue());

				list.add(item);
				// 以下是Rss源可先的几个部分
				System.out.println("标题的作者：" + entry.getAuthor());

				// 此标题所属的范畴
				List categoryList = entry.getCategories();
				if (categoryList != null)
				{
					for (int m = 0; m < categoryList.size(); m++)
					{
						SyndCategory category = (SyndCategory) categoryList.get(m);
						System.out.println("此标题所属的范畴：" + category.getName());
					}
				}

				// 得到流媒体播放文件的信息列表
				List enclosureList = entry.getEnclosures();
				if (enclosureList != null)
				{
					for (int n = 0; n < enclosureList.size(); n++)
					{
						SyndEnclosure enclosure = (SyndEnclosure) enclosureList.get(n);
						System.out.println("流媒体播放文件：" + entry.getEnclosures());
					}
				}
				System.out.println();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

}
