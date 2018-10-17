<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <!-- font awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    
    <!-- topMenu.css -->
    <link rel="stylesheet" type="text/css" href="resources/css/topMenu/topMenu.css">
    
    <!-- topMenu_projectManage.css-->
    <link rel="stylesheet" type="text/css" href="resources/css/topMenu/topMenu_projectManage.css">
    
    <!-- topMenu_projects.css -->
    <link rel="stylesheet" type="text/css" href="resources/css/topMenu/topMenu_projects.css">
    
    <!-- topMenu_activity.css -->
    <link rel="stylesheet" type="text/css" href="resources/css/topMenu/topMenu_activity.css">
    
    <!-- jQuery -->
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    
    <!-- topMenu.js -->
    <script src="resources/js/topMenu/topMenu.js"></script>
    </head>
    
    <body>   
       <table border="1px" style="width:100%; height:50px;">
            <tr>
                <td style="width:13%">
                    <i class="far fa-calendar-alt"></i>
                    tatab
                </td>
                <td style="width:7%" id="projectsBtn">
                   
                    <i class="fas fa-bolt"></i>
                    &nbsp; 
                    ABC
                    &nbsp; 
                    <i class="fas fa-angle-down"></i>
                </td>
                <td style=width:5%  id="infoBtn">
                   <i class="fas fa-info"></i> 
                </td>
                <td style=width:50%>
                </td>
                <td style=width:5% >
                    <table class="activeBtn">
                        <tr>
                            <td align="center">
                                9
                            </td>
                            <td rowspan="2">
                                <i class="fas fa-angle-down"></i>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Active
                            </td>
                        </tr>
                    </table>
                </td>
                <td style=width:5%>
                    <i class="far fa-clock"></i>
                </td>
                <td style=width:5% class="activityBtn">
                <i class="fas fa-at"></i>
                    <div onclick="history.back();" class="page_cover">   </div>
                    <div id="menu"> 
                    <div onclick="history.back();" class="activityClose"></div>
                    <!-- activity.jsp-->
                  	  <jsp:include page="/WEB-INF/views/topMenu/topMenu_activity.jsp"/>
                    </div>
                    
                </td>
                <td style=width:5%>
                    <i class="fas fa-cog"></i>
                </td>
                <td style=width:5%>
                    <i class="fas fa-user-circle"></i>
                </td>
            </tr>
        </table>
        
        <!-- projects Modal -->
        <div id="projectsModal" class="projectsmodal">
           Modal content 
          <div class="projects-modal-content">
          	<jsp:include page="/WEB-INF/views/topMenu/topMenu_projects.jsp"></jsp:include>
          </div>
        </div>
        <!-- projects Modal -->
    
        <!-- projectManage Modal -->
        <div id="manageModal" class="manageModal">
            <!-- Modal content -->
            <div class="manage-modal-content">
            	<jsp:include page="/WEB-INF/views/topMenu/topMenu_projectManage.jsp"></jsp:include>
            </div>
        </div>
        <!-- projectManage Modal -->

    </body>
</html>