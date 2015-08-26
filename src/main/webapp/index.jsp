<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="baseURL" value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}" />
<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> 
<!--<![endif]-->
	<head>
		<meta charset="utf-8">
        <!--[if IE]>
        <meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
        <title>Lista de Compras</title>
		<!-- main metatags -->
		<meta property="og:type" content="website">
        <meta name="description" content="Lista de Compras">        
        <meta property="og:locale" content="en_US">
        <meta property="og:site_name" content="Portal Lista de Compras">
        <meta property="og:title" content="Lista de Compras">
       	<meta property="og:description" content="Lista de Compras">
		<!-- image metatags -->
        <meta property="og:image" content="/assets/img/lista_de_compras-logo.jpg">
		<meta property="og:image:width" content="450">
		<meta property="og:image:height" content="612">
        <!-- favicon.ico and apple-touch-icon.png -->
		<link rel="icon" href="/assets/img/lista_de_compras-favicon.ico" type="image/x-icon">
		<link rel="shortcut icon" href="/assets/img/lista_de_compras-favicon.ico" type="image/x-icon">
		<link rel="apple-touch-icon" href="/assets/img/lista_de_compras-favicon.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="/assets/css/custom.css">
        <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,200' rel='stylesheet' type='text/css'>
        <script>
        	baseURL = '';
        </script>
	</head>
	<body data-ng-app="shoppingListApp" data-ng-controller="MainController">
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
				  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				    <span class="sr-only">Toggle Navigation</span>
				    <span class="icon-bar"></span>
				    <span class="icon-bar"></span>
				    <span class="icon-bar"></span>
				  </button>
				  <a class="navbar-brand" href="#/">
				  	<span class="glyphicon glyphicon-list" aria-hidden="true"></span> Lista de Compras
				  </a>
				</div>
			</div>
	    </nav>
		<div class="container angular-main-container">
			<div class="row">
			  <div class="col-md-12" data-ng-view>
			  </div>
			</div>
			<hr>
		</div>
	    <div class="jumbotron">
			<div class="container">
				<div class="row">
					<div class="col-md-2 hidden-sm hidden-xs">
						<img class="home-header-img" alt="Logo lista de compras" src="/assets/img/lista_de_compras-logo.png">
					</div>
					<div class="col-md-10">
						<h2>Bem Vindo ao Lista de Compras!!!</h2>
						<p>
							Agora você pode controlar suas compras com muito mais facilidade. Aqui você pode:
							<ul class="list-group">
								<li class="list-group-item"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>  Criar novas listas de compras</li>
								<li class="list-group-item"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>  Encontrar suas listas de compras anteriores</li>
								<li class="list-group-item"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>  Editar suas listas de compras</li>
								<li class="list-group-item"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>  Categorizar listas de compras</li>
								<li class="list-group-item"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>  Excluir listas de compras desnecessárias</li>
							</ul> 
						<p>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="container">
			<footer>
	        	<p>© Ronaldo Donida 2015</p>
	      	</footer>
		</div>
		
		<!-- JS Import -->
		<script src="/assets/js/jquery.min.js"></script>
		<script src="/assets/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/assets/js/angular.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.4/angular-resource.min.js"></script>
		<script type="text/javascript" src="/assets/js/angular-route.min.js"></script>
		<script type="text/javascript" src="https://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.13.3.js"></script>
		<script type="text/javascript" src="/assets/ng/app.js"></script>
		<script type="text/javascript" src="/assets/ng/routes.js"></script>
		<script type="text/javascript" src="/assets/ng/services/product-srv.js"></script>
		<script type="text/javascript" src="/assets/ng/services/category-srv.js"></script>
		<script type="text/javascript" src="/assets/ng/services/productlist-srv.js"></script>
		<script type="text/javascript" src="/assets/ng/controllers/main-ctrl.js"></script>
		<script type="text/javascript" src="/assets/ng/controllers/create-list-ctrl.js"></script>
		<script type="text/javascript" src="/assets/ng/controllers/select-products-ctrl.js"></script>
		<script type="text/javascript" src="/assets/ng/controllers/create-category-ctrl.js"></script>
		<script type="text/javascript" src="/assets/js/custom.js"></script>
	</body>
</html>
