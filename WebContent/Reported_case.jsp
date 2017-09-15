<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reported_case.model.*"%>
 <%Reported_caseVO reported_caseVO = (Reported_caseVO) request.getAttribute("reported_caseVO");%>

<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>會員資訊</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<!-- # linking piont local style css-->	
		<link rel="stylesheet" type="text/css" href="css/style.css">
		
<!-- # linking piont bootstrap-lightbox-->	
			<link rel="stylesheet" href="bootstrap-lightbox/bootstrap-lightbox.css">
<!-- # linking piont tab-icon-->
			<link rel="icon" type="images/gif" href="img/hc_icon_w.png" sizes="16x16">
			
		<style type="text/css">
.margin-top{
	margin-top:80px;
}

.margin-left{
	margin-left:15px;
}

.margin-right: {
	margin-right:60px;
}


.font-record{
	color:#910ece;
}

 .text-right{
 	 text-align: right;
 }
.text-center{
	 text-align: center;
}
.title-bgcolor{
	background-color:#dadcdd;
}
.padding-15{
	padding:15px;
}
.hr-color-date{
	border-color: #ccc;

}
.cs-content-nb{
			padding: 15px;
}
		</style>
	</head>
	<body>




<!-- ======================================================================================= -->
		<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">選單切換</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
<!-- # linking piont 首頁-->
					<a class="navbar-brand" href="index.html"><img src="img/homecode2.png" height="35" class="img-va"></a>

				</div>
				
				<!-- 手機隱藏選單區 -->
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					
					<!-- 左選單 -->
					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a href="#" class="collapse-toggle" data-toggle="dropdown">接手案件 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">申請中案件</a></li>
<!-- # linking piont Timeline-->
								<li><a href="member_case.html">進行中案件</a></li>
								<li><a href="#">已完成案件</a></li>
								<li><a href="#">接案評價</a></li>
							</ul>
						</li>
					</ul>

					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a href="#" class="collapse-toggle" data-toggle="dropdown">發包案件 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="member_post.html">張貼案件</a></li>
								<li><a href="#">等待中案件</a></li>
<!-- # linking piont Timeline-->
								<li><a href="member_case.html">進行中案件</a></li>
								<li><a href="#">已完成案件</a></li>
								<li><a href="#">發案評價</a></li>

							</ul>
						</li>
					</ul>

					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a href="#" class="collapse-toggle" data-toggle="dropdown">訊息中心 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">HomeCode! 訊息</a></li>
								<li><a href="#">會員訊息</a></li>
							</ul>
						</li>
					</ul>
					
					<form class="navbar-form navbar-left ng-pristine margin01">
						<div class="input-group">
							<div class="dropdown input-group-btn collapse-toggle">
								<label class="btn dropdown-toggle" data-toggle="dropdown">
									<span class="glyphicon glyphicon-search "></span>
									<span class="glyphicon glyphicon-chevron-down "></span>
								</label>
								<ul class="dropdown-menu">
								      <li><a href="#">尋找案件</a></li>
								      <li><a href="#">尋找人才</a></li>
							    </ul>
							</div>
							<input type="text" class="form-control" placeholder=" 找設計人才/工作案件">
						</div>
					</form>
					
					
				
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#" class="glyphicon glyphicon-question-sign navicon"> </a></li>
						<li><a href="#" class="glyphicon glyphicon-bell navicon"> </a></li>
						<li class="dropdown">
							<a href="#" class="glyphicon glyphicon-user collapse-toggle" data-toggle="dropdown"> <span class="font-18"> user-name</span> <span class="glyphicon glyphicon-chevron-down"></span></a>
							<ul class="dropdown-menu">
							      <li><a href="member_timeline.html">user-name</a></li>
							      <li><a href="#">設定</a></li>
							      <li><a href="#">登出</a></li>
						    </ul>

						</li>
						
					</ul>
					
				</div>
			<!-- 手機隱藏選單區結束 -->
			</div>
		</nav>





<!-- ============================================================================feedback========================================================================-->
			
	<div class="container mb20 margin-top cen">

	<div class=" mrg-top-15  col-xs-12  ">
			<div class=" title-bgcolor row margin-right-15">
			<h3>網路建置與維護</h3>

			</div>

			<div class=" cs-content row">
				<div class="col-xs-12 row ">
			
				<div class="mrg-top-15 "><h3>檢舉案件</h3></div>
				<hr class="hr-color">
				
			</div>


				<div class="mrg-top-15 cs-content-nb row ">
			<div class="row ">
			
					<div class="col-xs-12 " >

					<div class="col-xs-12 col-sm-6 ">
			<div class="font-content"><input type="radio" name="reportedmember" value="案件為廣告">案件為廣告<br></div>
					<div class="font-content"><input type="radio" name="reportedmember" value="工作內容與實際不一致">工作內容與實際不一致<br></div>
					<div class="font-content"><input type="radio" name="reportedmember" value="濫發訊息">濫發訊息<br></div>
					<div class="font-content"><input type="radio" name="reportedmember" value="無故解約">無故解約<br></div>
					<div class="font-content"><input type="radio" name="reportedmember" value="要求免費工作">要求免費工作<br></div>
					<div class="font-content"><input type="radio" name="reportedmember" value="私下交易">私下交易<br></div>
					</div>
					<div class="col-xs-12 col-sm-6 ">
					<!-- <div><p></p></div>
					<div><p></p></div>
					<div><p></p></div> -->
					</div>

					</div>

					

				<!-- 	<div class="font-content"><p >$100000</p></div> -->
				
					</div>

				</div>
				<div class="mrg-top-15"><h4>檢舉描述</h4>
				</div>
					<textarea class="form-control" rows="5" id="comment" laceholder="請詳細檢舉內容"></textarea>
				<hr class="hr-color">
					<div class="form-gruop mrg-top-15">
					<div class="img-va central">
					<button type="button" class="btn btn-default  btn-tab-4">送出</button>	
					</div>
					</div>	
				</div>
			</div>
		</div>


	</div>
<!--=====================================我要檢舉+發送訊息==================================-->	

			<!-- <div class="col-xs-12  margin-top" >
			<div class=" vh_center_right  ">
				<span>
					<button type="button" class="btn btn-default btn-square ">
	          			<span class="glyphicon glyphicon-flag"></span> 我要檢舉
	        		</button>
        		</span>
			</div>
			<button type="button" class="btn btn-default btn-square">發送訊息</button>
			<button type="button" class="btn btn-default btn-square">聯絡接案人</button>

				<hr class="hr-color">
				<p>
					<h3>接案者資訊</h3>
				</p>
				<p>					
					<span >總評</span>

				</p>
				<p>
					
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
				</p>
				<p>
					接案成功數<br>
					<span class="hirersuccess font-content">10</span>

				</p>
				<p>
					接案總數<br>
					<span class="hirercount font-content">15</span>

				</p>
				<p>
					<span class="font-content">Member Since 2017 09月 05</span>
				</p>
		</div>
	</div>
 -->
<!--=====================================作品集==================================-->				

<!--=====================================評價================================-->

<!-- <div class="container Portfolio">
		<div class="row iv-d">
			<div class="col-xs-12 col-sm-6 col-sm-push-2">
				<div class="h2">經歷</div>
				<P>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis odio, voluptatum perspiciatis, neque tempore magnam possimus nemo pariatur ex qui sed, aliquam, maiores expedita atque deserunt animi? Repellat eius, pariatur nobis deleniti ut iusto saepe minus, cupiditate aspernatur laborum eligendi veritatis ipsa odio nemo accusamus dignissimos cumque repudiandae quia. Magni natus iste magnam libero aperiam ipsa error, omnis odit harum quisquam, alias reprehenderit quas deleniti! Quo neque, dignissimos quibusdam dolore maxime quisquam ducimus distinctio eaque in vero. Amet cupiditate ex ad quasi! Magnam quasi provident labore, incidunt aspernatur ut quia!</P>
					</div>
				<div class="col-xs-12 col-sm-6 col-sm-pull-10">
			</div>
		</div>
	</div> -->

	<!--=====================================================================-->

	<!--=====================================================================-->

	<!--=========================================================================================-->		


</div>






<!-- footer區 =========================================================== -->
		<div class="footer">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-3">
						<h3>title</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem provident dolorum veniam, iure voluptate veritatis a debitis architecto iste deserunt aperiam consequatur, officia sequi dolore. Odio, quam ratione magnam fuga itaque? Dicta libero mollitia voluptates neque molestias, harum magnam amet.</p>
					</div>
					<div class="col-xs-12 col-sm-3">
						<h3>title</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Deleniti sapiente doloribus repellendus exercitationem optio veniam quidem porro quis eaque, hic corrupti nobis voluptatum praesentium nemo cum est expedita minus temporibus, commodi fuga nisi. Quis velit, enim earum, provident ab odit!</p>
					</div>
					<div class="col-xs-12 col-sm-3">
						<h3>title</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Excepturi illo distinctio possimus quas et fuga ad enim quod eius iure inventore perspiciatis obcaecati eveniet at consequatur sed animi, itaque sapiente necessitatibus vel quam illum esse exercitationem culpa. Beatae, nisi inventore?</p>
					</div>
					<div class="col-xs-12 col-sm-3">
						<h3>title</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quia voluptatem necessitatibus quaerat iste ad aliquam earum soluta vitae corporis, nulla sed iusto odit, sunt molestias exercitationem quibusdam ex veniam deserunt beatae? Possimus fugiat cum omnis blanditiis ab ipsum, deleniti facilis!</p>
					</div>
				</div>
			</div>
		</div>
<!-- footer區結束 =========================================================== -->



	
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="js/script.js"></script> 
	</body>
</html>