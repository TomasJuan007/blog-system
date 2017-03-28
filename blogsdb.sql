/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : blogsdb

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-22 18:04:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blogstable`
-- ----------------------------
DROP TABLE IF EXISTS `blogstable`;
CREATE TABLE `blogstable` (
  `BLOGS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BLOGS_TITLE` varchar(255) NOT NULL,
  `BLOGS_CONTENT` text NOT NULL,
  `VIEW_COUNT` int(11) NOT NULL DEFAULT '0',
  `COMMENTCOUNT` int(11) NOT NULL DEFAULT '0',
  `SUPPORT` int(11) NOT NULL DEFAULT '0',
  `NONSUPPORT` int(11) NOT NULL DEFAULT '0',
  `RATE` double(11,4) NOT NULL DEFAULT '0.0000',
  `USER_ID` int(11) NOT NULL,
  `CREATETIME` datetime NOT NULL,
  PRIMARY KEY (`BLOGS_ID`),
  KEY `USER` (`USER_ID`),
  KEY `BLOGS_ID` (`BLOGS_ID`,`USER_ID`),
  CONSTRAINT `USER` FOREIGN KEY (`USER_ID`) REFERENCES `usertable` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogstable
-- ----------------------------
INSERT INTO `blogstable` VALUES ('6', '五个网络应用类型，你知道吗？', '即时通讯，搜索引擎，网络新闻，网络视频，在线音乐', '0', '0', '0', '0', '0.0000', '3', '2017-01-12 20:59:34');
INSERT INTO `blogstable` VALUES ('21', '周小川谈汇率稳定:特朗普当选后美元指数上升很猛', '问：\r\n2016年下半年开始，汇率出现了比较大幅度的变化，总理在政府工作报告中也提出，要坚持汇率市场化改革的方向，同时也要稳定人民币在全球货币当中的地位。请问2017年央行有哪些工作保障汇率的稳定？谢谢。\r\n答：\r\n您刚才提到的2016年下半年汇率波动比较大一些，有多种因素，我简单说两个。\r\n第一，2016年下半年，中国对外投资和其他方面的外部花销比较猛一些，每年下半年这个季节都会多一些，去年多得明显一些，也包括有一些企业在外面收购的热情。\r\n第二，美国大选，特朗普当选，之后出现了很多和一般人预期不太符合的变化，因此导致美元指数上升比较猛。在这种情况下，汇率波动比较大。\r\n我们相信，今年随着中国经济比较稳定，而且更加健康，供给侧结构性改革、“三去一降一补”都取得成绩，国际上对中国经济的信心也比较好，应该说汇率自动就有一个稳定的趋势。与此同时，我们有关政策方面没有什么太大的变化，但是在执行和监管方面要做得更精细一些。因此，我们相信在这样的情况下，今年的人民币汇率应该比较稳定。\r\n当然，外汇市场历来是非常敏感的一个市场，会随着整个全球经济，也随着中国所发生的各种事件不断波动，谁也不能够非常准确预期2017年走下来还会有哪些不确定性，哪些事件会发生。因此，正常的汇率波动是一个常态，也是一个正常的情况。', '0', '0', '0', '0', '0.0000', '7', '2017-03-10 11:39:31');
INSERT INTO `blogstable` VALUES ('56', '常见算法(片段)', '4、一种怪异的节点删除方式\r\n链表节点值类型为int型，给定一个链表中的节点node，但不给定整个链表的头节点，如何在链表中删除node？请实现这个函数,并分析这么会出现哪些问题。要求时间复杂度为O(1)。\r\n分析：方法是1->2->3，如删除2，可以将3复制给2变成1->3->3, 然后再删除3，得到1->3（实质是删除了当前结点的后一个结点）\r\n此方法存在的问题有：\r\n(1)如果node指向的就是最后一个结点，则将无法删除\r\n(2)可能外部对每个结点都有依赖，上述方法则会有问题\r\n \r\n 5、设计可以变更的缓存结构\r\n【题目】\r\n设计一种缓存结构，该结构在构造时确定大小，假设大小为 K，并有两个功能：\r\n1，set(key,value)：将记录(key,value)插入该结构。\r\n2，get(key)：返回key对应的value值。\r\n【要求】\r\n1，set和get方法的时间复杂度为O(1)。\r\n2，某个key的set或get操作一旦发生，认为这个 key 的记录成了最经常使用的。 \r\n3，当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。\r\n【举例】\r\n假设缓存结构的实例是cache，大小为3，并依次发生如下行为:\r\n1，cache.set(\"A\",1)。最经常使用的记录为(\"A\",1)。 \r\n2，cache.set(\"B\",2)。最经常使用的记录为(“B”,2)，(“A”,1)变为最不经常的。\r\n3，cache.set(\"C\",3)。最经常使用的记录为(“C”,2)，(“A”,1)还是最不经常的。 \r\n4，cache.get(\"A\")。最经常使用的记录为(“A”,1)，(“B”,2)变为最不经常的。 \r\n5，cache.set(\"D\",4)。大小超过了 3，所以移除此时最不经常使用的记录(“B”,2)，加入记录(“D”,4)，并且为最经常使用的记录，然后(\"C\",2)变为最不经常使用的记录。\r\n分析：采用双端链表的数据结构，因为要快速地移动一个元素,另外需要两个hash表(主要是为了查找和删除)，\r\n保存键值到结点值和结点值到键值的对应关系\r\n\r\n', '0', '0', '0', '0', '0.0000', '7', '2017-03-13 19:24:57');
INSERT INTO `blogstable` VALUES ('69', 'UGC （互联网术语）', '<a href=\"http://baike.baidu.com/link?url=yNygyBcUQsLdtS2YlPAjQByaFyAnAn_8u7SFGVv30du5kIyzVduTCRka0wFjJVp0MRqaxF2jVJc2aXZhQPZ60q\">查看原文</a>\r\n<h3>UGC（User Generated Content）指用户原创内容，是伴随着以提倡个性化为主要特点的Web2.0概念而兴起的。它并不是某一种具体的业务，而是一种用户使用互联网的新方式，即由原来的以下载为主变成下载和上传并重。随着互联网运用的发展，网络用户的交互作用得以体现，用户既是网络内容的浏览者，也是网络内容的创造者。互联网术语，全称为User Generated Content，也就是用户生成内容的意思。UGC的概念最早起源于互联网领域，即用户将自己原创的内容通过互联网平台进行展示或者提供给其他用户。UGC是伴随着以提倡个性化为主要特点的Web2.0概念兴起的。</h3>\r\n<div></div>\r\n<div>\r\n	<h2>概念介绍</h2>\r\n</div>\r\n<div>在web2.0时代，网络上内容的产出主要是由用户，每一个用户都可以生成自己的内容，互联网上的所有内容由用户创造，而不只是以前的某一些人，所以互联网上的内容会飞速增长，形成一个多、广、专的局面，对人类知识的积累和传播起到了一个非常大的作用，但要注意的是，因为每一个人都可以生成内容，可能会有很多错误、虚假和片面的内容，所以自己要判断。或许以后会有一个审核机制。</div>\r\n<div>UGC是“User Generated Content(用户原创内容)”的缩写。在一些组织中也将其称作 UCC（ User\r\n	created Content） 。UGC的概念最早起源于互联网领域，即用户将自己原创的内容通过互联网平台进行展示或者提供给其他用户。</div>\r\n<div>UGC是伴随着以提倡个性化为主要特点的Web2.0概念兴起的。UGC并不是某一种具体的业务，而是一种用户使用互联网的新方式，即由原来的以下载为主变成下载和上传并重。YouTube等网站都可以看做是UGC的成功案例，社区网络、视频分享、博客和播客(视频分享)等都是UGC的主要应用形式。</div>\r\n<div></div>\r\n<div>\r\n	<h2>网站分类</h2>\r\n</div>\r\n<div></div>\r\n<div>\r\n	<h3>好友社交网络</h3>\r\n</div>\r\n<div>\r\n	如Facebook，My\r\n	Space，开心网，人人网（校内），朋友网（QQ校友），众众网等。这类网站的好友大多在现实中也互相认识。用户可以更改状态，发表日志，发布照片，分享视频等，从而了解好友动态。\r\n</div>\r\n<div>\r\n	<h3>视频分享网络</h3>\r\n</div>\r\n<div>如YouTube，优酷网，土豆网，搜狐视频等。这类网站以视频的上传和分享为中心，它也存在好友关系，但相对于好友网络，这种关系很弱，更多地是通过共同喜好而结合。</div>\r\n<div></div>\r\n<div>\r\n	<h3>照片分享网络</h3>\r\n</div>\r\n<div>如Flickr，又拍网，图钉等。这类网站的特点与视频分享网站类似，只不过主体是照片，图片等。</div>\r\n<div></div>\r\n<div>\r\n	<h3>知识分享网络</h3>\r\n</div>\r\n<div>如百度百科、百度知道、维基百科等。这类网站是为了普及网友的知识和为网友解决疑问的。</div>\r\n<div></div>\r\n<div>\r\n	<h3>社区论坛</h3>\r\n</div>\r\n<div>如百度贴吧，天涯社区，知乎等。这类网站的用户往往因共同的话题而聚集在一起。</div>\r\n<div></div>\r\n<div>\r\n	<h3>微博</h3>\r\n</div>\r\n<div>如Twitter，新浪微博等。微博应该是2012年最流行的互联网应用了，它解决了信息的实时更新问题。手机等便携设备的普及让每一个微博用户都有可能成为第一现场的发布者。</div>\r\n<div></div>\r\n<div>\r\n	<h2>成功案例</h2>\r\n</div>\r\n<div></div>\r\n<div>\r\n	<h3>WIKI</h3>\r\n</div>\r\n<div>\r\n	<b>WIKI：最大也是最小的百科全书</b>\r\n</div>\r\n<div>WIKI指的是一种网上共同协作的超文本系统，可由多人共同对网站内容进行维护和更新，是典型的靠UGC运作的系统。其中，WIKI利用UGC概念，使网站的内容制作和编辑成本最小化，但是能够实现领域知识的积累和最大化。</div>\r\n<div>用户可以通过网页浏览器对WIKI文本进行浏览、创建、更改，与其他超文本系统相比，WIKI有使用方便及开放的特点，所以WIKI系统可以帮助用户在一个社群内共同收集、创作某领域的知识，发布所有领域用户都关心和感兴趣的话题。WIKI使用了UGC概念，就蕴含“与他人同创共享”的理念。</div>\r\n<div>某WIKI系统的开发者曾经指出，WIKI是一种纯粹的用户内容服务，如果网站的诸多内容都指向其域名，那么，搜索引擎将会被更多用户发现，也将会吸引更多的用户的参与。(晓黎)</div>\r\n<div></div>\r\n<div>\r\n	<h3>豆瓣网</h3>\r\n</div>\r\n<div>\r\n	<b>豆瓣网：UGC的聚合力量</b>\r\n</div>\r\n<div>\r\n	豆瓣网，创办于2005年3月，几乎没有任何商业宣传，截至2012年拥有5600多万注册用户，ALEXA排名稳定在1600名左右。原因在于其独特的内容生成机制。\r\n	<div></div>\r\n	豆瓣网所有的内容，分类，筛选，排序都由成员产生和决定，完全自动。\r\n</div>\r\n<div>在豆瓣网，用户和用户之间有很多互动的可能。豆瓣内容形成的起点，是主动型的网民提供的自己所读过的书、看过的电影、听过的音乐的清单，相关评论和博客。这些内容提供了很多个基础节点，这些节点之间又因为网站技术系统所提供的相应功能，例如条目、“标签”或网站推荐，开始产生各种联系，从而编织出内容的基本网络。</div>\r\n<div>豆瓣的社区提供了一种以“兴趣爱好”为纽带扩展人际关系的可能。这种关系的形成无需刻意，它更多地是伴随着内容关系的形成而自然形成的。但是，也正是这种基于兴趣的人际关系，更加富有黏性，更加牢固。</div>\r\n<div></div>\r\n<div>\r\n	<h3>优酷土豆</h3>\r\n</div>\r\n<div>作为视频行业优酷土豆来说，UGC是其自创始之初就流淌的基因，对UGC的支持也不在话下，从内容激活与流通、UGC作者成长与输送到UGC品牌等几大方面。UGC的发展得到了不断加码支持。</div>\r\n<div>2013年6月优酷、土豆分别推出“优酷分享计划”和“土豆播客分成计划”，推出就受到原创者的欢迎。而优酷土豆在对UGC支持上，仅Q3季度分给视频作者的金额就高达1000万，像“暴走漫画”、“李洪绸”等UGC创作大户，更是早就迈入了优酷土豆“百万富翁”的阵营。</div>\r\n<div>得益于优酷土豆平台影响力，多个UGC项目取得成功。分成计划除了吸引大量生长于互联网土壤的“土著”，更吸引了大量90后的优秀新生力量：如搞笑类的《90后的秀》等。\r\n</div>\r\n<div>优酷土豆的分成计划不仅开创了国内针对原创视频分成的先河，孵化了众多成功案例，更是凭借相对成熟的体系和绝对的规模优势成为行业的标杆。</div>\r\n<div>首先，优酷、土豆已然成为用户发现和观看创意视频的首选平台，5亿用户量、稳居行业第一的视频播放量等成为平台的硬实力的指标。这不仅为原创作者提供了分成保障，也得到了广告主的青睐，吸引了包括雪花啤酒在内的一批广告主在优质播客的频道上定向投放广告，进一步提升了UGC品牌的影响力，增加了原创作者的收入。</div>\r\n<div>其次，优酷土豆严格按照广告“营收”给原创者分成，原创者随时能通过后台查询视频播放量和收益，直接透明。</div>\r\n<div>再次，优酷土豆还研发了一系列新产品、新技术进一步实现原创者利益，比如率先开通了“视频认领”业务，帮助播客识别出侵权视频，保护原创版权。\r\n</div>\r\n<div></div>', '0', '0', '0', '0', '0.0000', '3', '2017-03-15 00:19:26');
INSERT INTO `blogstable` VALUES ('70', '测试区', 'UGC（User Generated Content）指用户原创内容，是伴随着以提倡个性化为主要特点的Web2.0概念而兴起的。它并不是某一种具体的业务，而是一种用户使用互联网的新方式，即由原来的以下载为主变成下载和上传并重。随着互联网运用的发展，网络用户的交互作用得以体现，用户既是网络内容的浏览者，也是网络内容的创造者。互联网术语，全称为User Generated Content，也就是用户生成内容的意思。UGC的概念最早起源于互联网领域，即用户将自己原创的内容通过互联网平台进行展示或者提供给其他用户。UGC是伴随着以提倡个性化为主要特点的Web2.0概念兴起的。\r\n<div></div>\r\n<div>\r\n	<h2>概念介绍</h2>\r\n</div>\r\n<div>在web2.0时代，网络上内容的产出主要是由用户，每一个用户都可以生成自己的内容，互联网上的所有内容由用户创造，而不只是以前的某一些人，所以互联网上的内容会飞速增长，形成一个多、广、专的局面，对人类知识的积累和传播起到了一个非常大的作用，但要注意的是，因为每一个人都可以生成内容，可能会有很多错误、虚假和片面的内容，所以自己要判断。或许以后会有一个审核机制。</div>\r\n<div>UGC是“User Generated Content(用户原创内容)”的缩写。在一些组织中也将其称作 UCC（ User\r\n	created Content） 。UGC的概念最早起源于互联网领域，即用户将自己原创的内容通过互联网平台进行展示或者提供给其他用户。</div>\r\n<div>UGC是伴随着以提倡个性化为主要特点的Web2.0概念兴起的。UGC并不是某一种具体的业务，而是一种用户使用互联网的新方式，即由原来的以下载为主变成下载和上传并重。YouTube等网站都可以看做是UGC的成功案例，社区网络、视频分享、博客和播客(视频分享)等都是UGC的主要应用形式。</div>\r\n<div></div>\r\n<div>\r\n	<h2>网站分类</h2>\r\n</div>\r\n<div></div>\r\n<div>\r\n	<h3>好友社交网络</h3>\r\n</div>\r\n<div>\r\n	如Facebook，My\r\n	Space，开心网，人人网（校内），朋友网（QQ校友），众众网等。这类网站的好友大多在现实中也互相认识。用户可以更改状态，发表日志，发布照片，分享视频等，从而了解好友动态。\r\n</div>\r\n<div>\r\n	<h3>视频分享网络</h3>\r\n</div>\r\n<div>如YouTube，优酷网，土豆网，搜狐视频等。这类网站以视频的上传和分享为中心，它也存在好友关系，但相对于好友网络，这种关系很弱，更多地是通过共同喜好而结合。</div>\r\n<div></div>\r\n<div>\r\n	<h3>照片分享网络</h3>\r\n</div>\r\n<div>如Flickr，又拍网，图钉等。这类网站的特点与视频分享网站类似，只不过主体是照片，图片等。</div>\r\n<div></div>\r\n<div>\r\n	<h3>知识分享网络</h3>\r\n</div>\r\n<div>如百度百科、百度知道、维基百科等。这类网站是为了普及网友的知识和为网友解决疑问的。</div>\r\n<div></div>\r\n<div>\r\n	<h3>社区论坛</h3>\r\n</div>\r\n<div>如百度贴吧，天涯社区，知乎等。这类网站的用户往往因共同的话题而聚集在一起。</div>\r\n<div></div>\r\n<div>\r\n	<h3>微博</h3>\r\n</div>\r\n<div>如Twitter，新浪微博等。微博应该是2012年最流行的互联网应用了，它解决了信息的实时更新问题。手机等便携设备的普及让每一个微博用户都有可能成为第一现场的发布者。</div>\r\n<div></div>\r\n<div>\r\n	<h2>成功案例</h2>\r\n</div>\r\n<div></div>\r\n<div>\r\n	<h3>WIKI</h3>\r\n</div>\r\n<div>\r\n	<b>WIKI：最大也是最小的百科全书</b>\r\n</div>\r\n<div>WIKI指的是一种网上共同协作的超文本系统，可由多人共同对网站内容进行维护和更新，是典型的靠UGC运作的系统。其中，WIKI利用UGC概念，使网站的内容制作和编辑成本最小化，但是能够实现领域知识的积累和最大化。</div>\r\n<div>用户可以通过网页浏览器对WIKI文本进行浏览、创建、更改，与其他超文本系统相比，WIKI有使用方便及开放的特点，所以WIKI系统可以帮助用户在一个社群内共同收集、创作某领域的知识，发布所有领域用户都关心和感兴趣的话题。WIKI使用了UGC概念，就蕴含“与他人同创共享”的理念。</div>\r\n<div>某WIKI系统的开发者曾经指出，WIKI是一种纯粹的用户内容服务，如果网站的诸多内容都指向其域名，那么，搜索引擎将会被更多用户发现，也将会吸引更多的用户的参与。(晓黎)</div>\r\n<div></div>\r\n<div>\r\n	<h3>豆瓣网</h3>\r\n</div>\r\n<div>\r\n	<b>豆瓣网：UGC的聚合力量</b>\r\n</div>\r\n<div>\r\n	豆瓣网，创办于2005年3月，几乎没有任何商业宣传，截至2012年拥有5600多万注册用户，ALEXA排名稳定在1600名左右。原因在于其独特的内容生成机制。\r\n	<div></div>\r\n	豆瓣网所有的内容，分类，筛选，排序都由成员产生和决定，完全自动。\r\n</div>\r\n<div>在豆瓣网，用户和用户之间有很多互动的可能。豆瓣内容形成的起点，是主动型的网民提供的自己所读过的书、看过的电影、听过的音乐的清单，相关评论和博客。这些内容提供了很多个基础节点，这些节点之间又因为网站技术系统所提供的相应功能，例如条目、“标签”或网站推荐，开始产生各种联系，从而编织出内容的基本网络。</div>\r\n<div>豆瓣的社区提供了一种以“兴趣爱好”为纽带扩展人际关系的可能。这种关系的形成无需刻意，它更多地是伴随着内容关系的形成而自然形成的。但是，也正是这种基于兴趣的人际关系，更加富有黏性，更加牢固。</div>\r\n<div></div>\r\n<div>\r\n	<h3>优酷土豆</h3>\r\n</div>\r\n<div>作为视频行业优酷土豆来说，UGC是其自创始之初就流淌的基因，对UGC的支持也不在话下，从内容激活与流通、UGC作者成长与输送到UGC品牌等几大方面。UGC的发展得到了不断加码支持。</div>\r\n<div>2013年6月优酷、土豆分别推出“优酷分享计划”和“土豆播客分成计划”，推出就受到原创者的欢迎。而优酷土豆在对UGC支持上，仅Q3季度分给视频作者的金额就高达1000万，像“暴走漫画”、“李洪绸”等UGC创作大户，更是早就迈入了优酷土豆“百万富翁”的阵营。</div>\r\n<div>得益于优酷土豆平台影响力，多个UGC项目取得成功。分成计划除了吸引大量生长于互联网土壤的“土著”，更吸引了大量90后的优秀新生力量：如搞笑类的《90后的秀》等。\r\n</div>\r\n<div>优酷土豆的分成计划不仅开创了国内针对原创视频分成的先河，孵化了众多成功案例，更是凭借相对成熟的体系和绝对的规模优势成为行业的标杆。</div>\r\n<div>首先，优酷、土豆已然成为用户发现和观看创意视频的首选平台，5亿用户量、稳居行业第一的视频播放量等成为平台的硬实力的指标。这不仅为原创作者提供了分成保障，也得到了广告主的青睐，吸引了包括雪花啤酒在内的一批广告主在优质播客的频道上定向投放广告，进一步提升了UGC品牌的影响力，增加了原创作者的收入。</div>\r\n<div>其次，优酷土豆严格按照广告“营收”给原创者分成，原创者随时能通过后台查询视频播放量和收益，直接透明。</div>\r\n<div>再次，优酷土豆还研发了一系列新产品、新技术进一步实现原创者利益，比如率先开通了“视频认领”业务，帮助播客识别出侵权视频，保护原创版权。\r\n</div>\r\n<div></div>', '0', '0', '0', '0', '0.0000', '3', '2017-03-15 11:04:59');
INSERT INTO `blogstable` VALUES ('71', '新编辑器是不是会好看一点', '答案是并不会，结题！', '0', '0', '0', '0', '0.0000', '10', '2017-03-19 19:57:14');

-- ----------------------------
-- Table structure for `commenttable`
-- ----------------------------
DROP TABLE IF EXISTS `commenttable`;
CREATE TABLE `commenttable` (
  `COMMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `BLOGS_ID` int(11) NOT NULL,
  `COMMENT_CONTENT` varchar(140) NOT NULL,
  `CREATETIME` datetime NOT NULL,
  PRIMARY KEY (`COMMENT_ID`),
  KEY `CBLOGS` (`BLOGS_ID`),
  KEY `CUSER` (`USER_ID`),
  CONSTRAINT `CBLOGS` FOREIGN KEY (`BLOGS_ID`) REFERENCES `blogstable` (`BLOGS_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CUSER` FOREIGN KEY (`USER_ID`) REFERENCES `usertable` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commenttable
-- ----------------------------

-- ----------------------------
-- Table structure for `standpointtable`
-- ----------------------------
DROP TABLE IF EXISTS `standpointtable`;
CREATE TABLE `standpointtable` (
  `STANDPOINT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BLOGS_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `TYPE` tinyint(4) NOT NULL,
  PRIMARY KEY (`STANDPOINT_ID`),
  UNIQUE KEY `SINGLE` (`BLOGS_ID`,`USER_ID`) USING BTREE,
  KEY `SPBLOG` (`BLOGS_ID`) USING BTREE,
  KEY `SPUSER` (`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of standpointtable
-- ----------------------------

-- ----------------------------
-- Table structure for `usertable`
-- ----------------------------
DROP TABLE IF EXISTS `usertable`;
CREATE TABLE `usertable` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ACCOUNT` varchar(50) NOT NULL,
  `USER_NAME` varchar(50) NOT NULL,
  `USER_PASSWORD` varchar(255) NOT NULL,
  `USER_SEX` char(1) DEFAULT NULL,
  `USER_PHONE` varchar(50) DEFAULT NULL,
  `CREATETIME` datetime NOT NULL,
  `UPDATETIME` datetime NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `account` (`USER_ACCOUNT`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usertable
-- ----------------------------
INSERT INTO `usertable` VALUES ('3', '123@123.com', 'Masahudu', '68BB0B8B1DE44EE386C4CC03BC32E45D00ED65C7C612438A06C0B8CD', 'M', '15622132133', '2017-01-12 20:54:12', '2017-03-16 00:28:18');
INSERT INTO `usertable` VALUES ('7', '123@12.12', '桃子', '48C88508C35E3939F285C104C0852BDAE17A4EB2C4439CC12644E5BC', 'F', '14232322232', '2017-03-10 09:50:02', '2017-03-10 09:50:02');
INSERT INTO `usertable` VALUES ('8', '123@123.123', '李白', '42068D941A40F4C4147A4A5C921655C29C33037697CFE347E6C33C36', 'M', '13712671267', '2017-03-19 19:50:15', '2017-03-19 19:50:15');
INSERT INTO `usertable` VALUES ('9', '111@11.11', 'Masahudu', '3F2C199896DDFC59D67D8E9D7801BB74D84622E90A575A212DEABA55', 'M', '15828982665', '2017-03-19 19:50:59', '2017-03-19 19:50:59');
INSERT INTO `usertable` VALUES ('10', '12@12.12', 'huangtao', '1DB282958E3B0D47135D9CC7C95D307868117FFA19B57A7FC477AEC9', 'M', '15665424562', '2017-03-19 19:52:16', '2017-03-19 19:52:16');
