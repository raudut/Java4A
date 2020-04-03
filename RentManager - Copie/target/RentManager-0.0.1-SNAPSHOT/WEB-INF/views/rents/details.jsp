<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-3">

                    <!-- Profile Image -->
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <h3 class="profile-username text-center">Reservation n°${reservation_id}</h3>

                            <ul class="list-group list-group-unbordered">                                                        
                            	<li class="list-group-item">
                                    <b>Date de debut</b> <a class="pull-right">${debut}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>Date de fin</b> <a class="pull-right">${fin}</a>
                                </li>                               
                            </ul>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
                <div class="col-md-9">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#rents" data-toggle="tab">Client</a></li>
                            <li><a href="#cars" data-toggle="tab">Vehicules</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="client">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Nom</th>
                                            <th>Prenom</th>
                                            <th>Email</th>
                                        </tr>
                                       
                                        <tr>
                                        	<td>${clients_id}</td>
                                            <td>${clients_nom}</td>
                                            <td>${clients_prenom}</td>
                                            <td>${clients_email}</td>
                                        </tr>
                                                                            
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="cars">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Contructeur</th>
                                            <th>Modele</th>
                                            <th Nombre de place</th>
                                        </tr>
                                        
                                        <tr>
                                        	<td>${voiture_id}</td>
                                            <td>${voiture_manufacturer}</td>
                                            <td>${voiture_modele}</td>
                                            <td>${voiture_seats}</td>
                                            <td>${clients_email}</td>
                                        </tr> 
                                                                         
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
