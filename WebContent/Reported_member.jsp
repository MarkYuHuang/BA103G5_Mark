<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reported_member.model.*"%>

 <%Reported_memberVO reported_memberVO = (Reported_memberVO) request.getAttribute("reported_memberVO");%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>�|����T</title>
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
						<span class="sr-only">������</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
<!-- # linking piont ����-->
					<a class="navbar-brand" href="index.html"><img src="img/homecode2.png" height="35" class="img-va"></a>

				</div>
				
				<!-- ������ÿ��� -->
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					
					<!-- ����� -->
					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a href="#" class="collapse-toggle" data-toggle="dropdown">����ץ� <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">�ӽФ��ץ�</a></li>
<!-- # linking piont Timeline-->
								<li><a href="member_case.html">�i�椤�ץ�</a></li>
								<li><a href="#">�w�����ץ�</a></li>
								<li><a href="#">���׵���</a></li>
							</ul>
						</li>
					</ul>

					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a href="#" class="collapse-toggle" data-toggle="dropdown">�o�]�ץ� <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="member_post.html">�i�K�ץ�</a></li>
								<li><a href="#">���ݤ��ץ�</a></li>
<!-- # linking piont Timeline-->
								<li><a href="member_case.html">�i�椤�ץ�</a></li>
								<li><a href="#">�w�����ץ�</a></li>
								<li><a href="#">�o�׵���</a></li>

							</ul>
						</li>
					</ul>

					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a href="#" class="collapse-toggle" data-toggle="dropdown">�T������ <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">HomeCode! �T��</a></li>
								<li><a href="#">�|���T��</a></li>
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
								      <li><a href="#">�M��ץ�</a></li>
								      <li><a href="#">�M��H�~</a></li>
							    </ul>
							</div>
							<input type="text" class="form-control" placeholder=" ��]�p�H�~/�u�@�ץ�">
						</div>
					</form>
					
					
				
					<!-- �k��� -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#" class="glyphicon glyphicon-question-sign navicon"> </a></li>
						<li><a href="#" class="glyphicon glyphicon-bell navicon"> </a></li>
						<li class="dropdown">
							<a href="#" class="glyphicon glyphicon-user collapse-toggle" data-toggle="dropdown"> <span class="font-18"> user-name</span> <span class="glyphicon glyphicon-chevron-down"></span></a>
							<ul class="dropdown-menu">
							      <li><a href="member_timeline.html">user-name</a></li>
							      <li><a href="#">�]�w</a></li>
							      <li><a href="#">�n�X</a></li>
						    </ul>

						</li>
						
					</ul>
					
				</div>
			<!-- ������ÿ��ϵ��� -->
			</div>
		</nav>

<!-- ============================================================================feedback========================================================================-->
			
	<div class="container mb20 margin-top cen">

	<div class=" mrg-top-15  col-xs-12  ">
			<div class=" title-bgcolor row margin-right-15">
			<h3>Mark Huang</h3>

			</div>

			<div class=" cs-content row">
				<div class="col-xs-12 row ">
			
				<div class="mrg-top-15 "><h3>���|�|��</h3></div>
				<hr class="hr-color">
				
			</div>


				<div class="mrg-top-15 cs-content-nb row ">
			<div class="row ">
			
					<div class="col-xs-12 " >

					<div class="col-xs-12 col-sm-6 ">
					<div class="font-content"><input type="radio" name="reportedmember" value="�갰�g��">�갰�g��<br></div>
					<div class="font-content"><input type="radio" name="reportedmember" value="���ƥL�H���">���ƥL�H���<br></div>
					<div class="font-content"><input type="radio" name="reportedmember" value="�ݵo�T��">�ݵo�T��<br></div>
					<div class="font-content"><input type="radio" name="reportedmember" value="�ӤH�@�~�H�ϰӼ��v">�ӤH�@�~�H�ϰӼ��v<br></div>
					<div class="font-content"><input type="radio" name="reportedmember" value="�H�����p�v">�H�����p�v<br></div>
					<div class="font-content"><input type="radio" name="reportedmember" value="�Ӥ��D���H">�Ӥ��D���H<br></div>
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
				<div class="mrg-top-15"><h4>���|�y�z</h4>
				</div>
					<textarea class="form-control" rows="5" id="comment" laceholder="�иԲ����|���e"></textarea>
				<hr class="hr-color">
					<div class="form-gruop mrg-top-15">
					<div class="img-va central">
					<button type="button" class="btn btn-default  btn-tab-4">�e�X</button>	
					</div>
					</div>	
				</div>
			</div>
		</div>


	</div>
<!--=====================================�ڭn���|+�o�e�T��==================================-->	

			<!-- <div class="col-xs-12  margin-top" >
			<div class=" vh_center_right  ">
				<span>
					<button type="button" class="btn btn-default btn-square ">
	          			<span class="glyphicon glyphicon-flag"></span> �ڭn���|
	        		</button>
        		</span>
			</div>
			<button type="button" class="btn btn-default btn-square">�o�e�T��</button>
			<button type="button" class="btn btn-default btn-square">�p�����פH</button>

				<hr class="hr-color">
				<p>
					<h3>���ת̸�T</h3>
				</p>
				<p>					
					<span >�`��</span>

				</p>
				<p>
					
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
				</p>
				<p>
					���צ��\��<br>
					<span class="hirersuccess font-content">10</span>

				</p>
				<p>
					�����`��<br>
					<span class="hirercount font-content">15</span>

				</p>
				<p>
					<span class="font-content">Member Since 2017 09�� 05</span>
				</p>
		</div>
	</div>
 -->
<!--=====================================�@�~��==================================-->				

</div>

<!-- footer�� =========================================================== -->
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
<!-- footer�ϵ��� =========================================================== -->



	
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="js/script.js"></script> 
	</body>
</html>
</body>
</html>