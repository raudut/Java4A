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
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Reservations
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/rents/change?id=${id}">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="client" class="col-sm-2 control-label">Client</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="client" name="client" value="${client}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="voiture" class="col-sm-2 control-label">Voiture</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="voiture" name="voiture" value="${voiture}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="debut" class="col-sm-2 control-label">Debut</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="debut" name="debut" value="${debut}" required
                                               data-inputmask="'alias': 'yyyy-mm-dd'" data-mask>>
                                    </div>
                                    <label for="first_name" class="col-sm-2 control-label">Fin</label>
                                    
                                     <div class="col-sm-10">
                                        <input type="text" class="form-control" id="fin" name="fin" value="${fin}"  required
                                               data-inputmask="'alias': 'yyyy-mm-dd'" data-mask>>
                                    </div>
                                </div>                                
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">Modifier</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
