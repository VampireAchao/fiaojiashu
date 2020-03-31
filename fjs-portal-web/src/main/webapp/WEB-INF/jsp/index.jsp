<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <title>覅甲蜀商城-覅甲蜀旗下全球美食优选网购商城-进口食品、母婴、营养保健品、生鲜、粮油、酒水饮料、休闲食品-覅甲蜀商城fiaojiashu.cn</title>
    <meta name="Keywords" content="进口食品,网上超市,网上购物,进口奶粉,覅甲蜀商城,sfbest,母婴用品,营养保健品,生鲜食品,粮油,酒水,休闲食品">
    <meta name="Description"
          content="覅甲蜀商城覅甲蜀旗下全球美食优选网购商城，精选来自60多个国家和地区的进口食品，正品行货，支持货到付款。销售包括进口奶粉、母婴用品、营养保健品、生鲜食品、粮油、酒水、休闲食品等近万种商品。">
    <link rel="dns-prefetch" href="//pic.fiaojiashu.cn">
    <meta property="wb:webmaster" content="3a008ad947166307">
    <link rel="stylesheet" type="text/css" href="/css/base_w1200.css?v=2016071333">
    <link rel="stylesheet" type="text/css" href="/css/index.css?v=2016071312">
    <script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="/js/global_index.js"></script>
    <script type="text/javascript" src="/js/vue.js"></script>
    <script type="text/javascript" src="/js/axios.min.js"></script>
    <style id="style-1-cropbar-clipper">
        .en-markup-crop-options {
            top: 18px !important;
            left: 50% !important;
            margin-left: -100px !important;
            width: 200px !important;
            border: 2px rgba(255, 255, 255, .38) solid !important;
            border-radius: 4px !important;
        }

        .en-markup-crop-options div div:first-of-type {
            margin-left: 0px !important;
        }
    </style>
</head>
<body>
<!-- header start -->
<jsp:include page="commons/header.jsp"/>
<jsp:include page="commons/mainmenu.jsp"/>
<!-- header end -->
<!----row1------->
<div class="slide_show" id="slide_show">
    <div class="indexW">
        <div id="index_slide" class="slide_wrap">
            <ol>
                <c:forEach items="${CONTENT_SETINTERVAL}" var="node" varStatus="status">
                    <li>
                        <a name="sfbest_hp_hp_focus_${status.index }" class="fore_pic trackref" href="${node.url }"
                           target="_blank">
                            <img id="lunbo_1" alt="${node.title }" src="${node.pic }">
                        </a>
                    </li>
                </c:forEach>

            </ol>
        </div>
        <div class="rSide" style="display: none;">
            <c:forEach items="${CONTENT_SETINTERVAL_RIGHT_IMG}" varStatus="status" var="item">
                <a name="sfbest_hp_hp_focus_right-ad${status.index+1}" class="a-img r-img${status.index+1} trackref"
                   href="${item.url}" target="_blank">
                    <img alt="加载中..." src="${item.pic}">
                    <div class="rmask"></div>
                </a>
            </c:forEach>
        </div>
    </div>
    <ul class="none" id="lunboNum">
        <c:forEach items="${CONTENT_SETINTERVAL }" varStatus="status">
            <li class="<c:if test="${status.index==0 }">cur</c:if>">${status.index+1 }</li>
        </c:forEach>
    </ul>
    <div class="indexbg" id="indexbg">
        <dl style="left: -1903px;">
            <dd style="width: 1903px; background: rgb(20, 16, 13);"></dd>
            <dd style="width: 1903px; background: rgb(119, 96, 86);"></dd>
            <dd style="width: 1903px; background: rgb(17, 12, 55);"></dd>
            <dd style="width: 1903px; background: rgb(239, 244, 248);"></dd>
            <dd style="width: 1903px; background: rgb(231, 32, 37);"></dd>
            <dd style="width: 1903px; background: rgb(128, 29, 92);"></dd>
            <dd style="width: 1903px; background: rgb(117, 144, 1);"></dd>
            <dd style="width: 1903px; background: rgb(253, 213, 29);"></dd>
        </dl>
    </div>
</div>
<!----row1 end------->
<!-- 口碑甄选 start -->
<div class="indexW mt1">
    <div class="bestbuy">
        <div class="b_left">
            <h2>优选必买<span></span></h2>
            <ul class="bbig" id="bigPerfect">
                <c:forEach items="${CONTENT_BIG_PERFECT}" var="item" varStatus="status">
                    <li class="price_list0" goods="${item.id}" eid="${item.categoryId}"
                        id="cx_b_37194_0">
                        <a href="${item.url}"
                           title="${item.title}" target="_blank">
                            <img class="lazy"
                                 src="${item.pic}"
                                 style="display: inline;">
                        </a>
                        <div class="gBtn p-btn bbtn" style="top: 260px;">
                            <a pid="${item.id}"
                               data_url="${item.url}"
                               href="javascript:void(0)" indexflag="1">加入购物车</a>
                        </div>
                        <div class="bprice" id="priceK_b_215383_0">
                            <span><sup>￥</sup></span>${item.subTitle}
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <ul class="bsmall" id="smallPerfect">
                <c:forEach items="${CONTENT_SMALL_PERFECT}" var="item" varStatus="status">
                    <li class="price_list0" goods="${item.id}" eid="${item.categoryId}"
                        id="cx_b_37194_1"><a
                            href="${item.url}"
                            title="${item.title}" target="_blank"><img class="lazy"
                                                                       src="${item.pic}"
                                                                       style="display: inline;"></a>
                        <div class="gBtn p-btn bbtn" style="top: 210px;">
                            <a pid="${item.id}"
                               data_url="${item.url}"
                               href="javascript:void(0)" indexflag="1">加入购物车</a>
                        </div>
                        <div class="bprice" id="priceK_b_37194_1">
                            <span><sup>￥</sup></span>27.8
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <!-- 口碑甄选 end -->
        <div class="rSide1">

            <div class="rImg2">
                <a name="sfbest_hp_hp_news_right-ad" class="trackref" href="${CONTENT_RIGHT_PERFECT[0].url}"
                   target="_blank"><img salt="9.2-9.5" src="${CONTENT_RIGHT_PERFECT[0].pic}"></a>
            </div>

            <div class="sfNews">
                <div class="rTitle"><h2>最新动态</h2><a href="#" target="_blank" class="more">更多&gt;</a></div>
                <ul>
                    <c:forEach items="${CONTENT_RIGHT_PERFECT}" varStatus="status" var="item" begin="1">
                        <li><a name="sfbest_hp_hp_news_1" href="${item.url}" target="_blank"
                               class=" red trackref"
                               title="${item.title}">${item.title}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="clr"></div>
</div>
<!--楼层 start-->

<div class="indexW mt2 ie6 fresh">
    <div class="category">
        <ul class="mTitle">
            <li>

                <h2>
                    <em></em>
                    <a name="sfbest_hp_hp_floor1_floor-category1" class="trackref"
                       href="#" target="_blank">水果</a>&nbsp;&nbsp;<a
                        name="sfbest_hp_hp_floor1_floor-category2" class="trackref"
                        href="#" target="_blank">蔬菜</a>
                </h2>
            </li>
        </ul>
        <div class="lCont"><a name="sfbest_hp_hp_floor1_left-ad" class="trackref" href="#"
                              target="_blank">
            <img alt="加载中..." class="lazy" src="${CONTENT_GOODS[0].pic}"
                 style="display: inline;"></a></div>
    </div>
    <div class="sfRight">
        <div class="subCont">
            <ul class="pList" id="floor-gap-1">

                <c:forEach items="${CONTENT_GOODS}" var="item" varStatus="status" begin="1">

                    <li class="price_list1" eid="${item.categoryId}" goods="${item.id}"
                        id="cx_l_218031_7_297">
                        <div class="pImg">
                            <a
                                    href="${item.url}"
                                    target="_blank" title="泰国金柚700-1200g"><img class="lazy"
                                                                               data="${item.pic}"
                                                                               alt="${item.title}"
                                                                               src="${item.pic}"
                                                                               style="display: block;"></a>
                            <div class="gBtn p-btn">
                                <a pid="${item.id}"
                                   data_url="${item.pic}"
                                   href="javascript:void(0)" indexflag="1">加入购物车</a>
                            </div>
                        </div>
                        <div class="title-a">
                            <a
                                    href="${item.url}"
                                    target="_blank" title="${item.title}">${item.title}</a>
                        </div>
                        <div class="price" id="${item.id}">
                            <b>￥${item.subTitle}</b>
                        </div>
                    </li>

                </c:forEach>

            </ul>
        </div>
        <div class="redge">
            <ul class="rHot">
                <li><a name="sfbest_hp_hp_floor1_Keywords1" class="trackref"
                       href="/productlist/search/?categoryId=8&amp;keyword=%E8%93%9D%E8%8E%93&amp;o=saleNum%3Adesc"
                       target="_blank">蓝莓</a></li>
                <li><a name="sfbest_hp_hp_floor1_Keywords2" class="trackref"
                       href="/productlist/search?inputBox=1&amp;categoryId=0&amp;keyword=%E6%A4%B0%E9%9D%92"
                       target="_blank">椰青</a></li>
                <li><a name="sfbest_hp_hp_floor1_Keywords3" class="trackref"
                       href="/productlist/search?inputBox=1&amp;categoryId=0&amp;keyword=%E7%81%AB%E9%BE%99%E6%9E%9C"
                       target="_blank">火龙果</a></li>
                <li><a name="sfbest_hp_hp_floor1_Keywords4" class="trackref"
                       href="/productlist/search/?categoryId=8&amp;keyword=%E6%B0%B4%E8%9C%9C%E6%A1%83&amp;o=saleNum"
                       target="_blank">水蜜桃</a></li>
                <li><a name="sfbest_hp_hp_floor1_Keywords5" class="trackref"
                       href="/productlist/search?inputBox=1&amp;categoryId=0&amp;keyword=%E7%89%9B%E6%B2%B9%E6%9E%9C"
                       target="_blank">牛油果</a></li>
                <li><a name="sfbest_hp_hp_floor1_Keywords6" class="trackref"
                       href="/productlist/search?inputBox=1&amp;categoryId=0&amp;keyword=%E4%BD%B3%E6%B2%9B"
                       target="_blank">佳沛</a></li>
                <li><a name="sfbest_hp_hp_floor1_Keywords7" class="trackref"
                       href="/productlist/search?inputBox=1&amp;categoryId=0&amp;keyword=%E6%96%B0%E5%A5%87%E5%A3%AB"
                       target="_blank">新奇士</a></li>
                <li><a name="sfbest_hp_hp_floor1_Keywords8" class="trackref" href="/fresh/6162-0-0-0-0-2-0-0-0-0-0.html"
                       target="_blank">加工蔬菜</a></li>
                <li><a name="sfbest_hp_hp_floor1_Keywords9" class="trackref"
                       href="/fresh/55-9695-0-0-0-2-0-0-0-0-0.html" target="_blank">加利利</a></li>
            </ul>
            <div class="clr"></div>
            <div class="rimg">

                <a name="sfbest_hp_hp_floor1_right-ad" class="ht1 trackref" href="/html/activity/1472547970.html"
                   target="_blank"><img alt="8.30-9.5" class="lazy"
                                        data="http://001.sfimg.cn/web/1dd1130a/1dd1130a9c0103f6ec8a13fa13f27641.jpg"
                                        src="http://001.sfimg.cn/web/1dd1130a/1dd1130a9c0103f6ec8a13fa13f27641.jpg"
                                        style="display: inline;"></a>
                <div class="rbutton"><a href="/html/activity/1472547970.html" target="_blank"></a></div>
            </div>
        </div>
    </div>
    <!----天天生鲜 -->
    <span class="clr"></span>
</div>
<!--楼层 end -->

<!-- footer start -->
<jsp:include page="commons/footer.jsp"/>
<!-- footer end -->
</body>
</html>