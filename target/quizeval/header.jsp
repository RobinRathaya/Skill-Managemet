<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Testify</title>
        <meta
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
            name="viewport"
        />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
            integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay"
            crossorigin="anonymous"
        />
        <link rel="stylesheet" href="css/ionicons.min.css" />
        <link rel="stylesheet" href="css/AdminLTE.min.css" />
        <link rel="stylesheet" href="css/skin-blue.min.css" /> 
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"
        />
        <link rel="stylesheet" href="css/Dashboard.css" />
        <link rel="stylesheet" href="css/style.css" />
        <!-- jQuery 3 -->
        <script src="js/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="js/adminlte.min.js"></script>
    </head>
    <!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper" style="overflow: hidden">
            <!-- Main Header -->
            <header class="main-header">
                <!-- Logo -->
                <a href="index2.html" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>A</b>DT</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Admin</b>Testify</span>
                </a>

                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a
                        href="#"
                        class="sidebar-toggle"
                        data-toggle="push-menu"
                        role="button"
                    >
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">FEATURES</li>
                        <!-- Optionally, you can add icons to the links -->
                        <!-- <li><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
        <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li> -->
                        <li class="treeview">
                            <a href="#"
                                ><i class="fa fa-question"></i>
                                <span>Questions</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="addtopic.jsp">Add Topic</a></li>
                                <li><a href="addquestions.jsp">Add Questions</a></li>
                                <li><a href="bulkquestions.jsp">Add Bulk Questions</a></li>
                                <li><a href="view.jsp">View Questions</a></li>
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"
                                ><i class="fa fa-code"></i> <span>Test</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="addtest.jsp">Create Test</a></li>
                                <li><a href="#">Modify Test</a></li>
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"
                                ><i class="fa fa-user"></i>
                                <span>Students</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="addstudent.jsp">Add Students</a></li>
                                <li><a href="bulkquestions.jsp">Add Bulk Students</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"
                                ><i class="fas fa-chart-pie"></i>
                                <span>Statistics</span></a
                            >
                        </li>
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <br />
                        <li>
                            <a href="#"
                                ><i class="fas fa-power-off"></i>
                                <span>Logout</span></a
                            >
                        </li>
                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>