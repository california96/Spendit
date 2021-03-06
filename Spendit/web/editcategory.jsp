<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<%@include file = "navbar.jsp"%>
<style>
    .btn-submit{
    background-color: #73C2FB;
    color: white;
    float: right;
  }
  </style>
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">Edit a Category</h1>             
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
              <li class="breadcrumb-item active">Edit Category</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <!-- left column -->
          <div class="col-md-12">
            <!-- general form elements -->
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Category Form</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form action="updatecategory.action" method = "post" autocomplete = "off" id = "category-form">
                 <input type = "hidden" name = "categoryID" value = "${category.id }">
              
                <div class="card-body">
                 <div class="form-group col-sm-3">
                    <label for="name">Current Image</label>
                    <img src = "retrieveimage.action?module=category&fileName=${category.image}" class="img-circle elevation-2 avatar" alt="User Image"/>
                    
                  </div>
                  <div class="form-group col-sm-3">
                    <label for="name">Category Name</label>
                      <input type = "text" name = "name" class = "form-control" id = "name" value = "${category.name }" onkeydown = "preventNumbers(event);" onkeyup = "preventNumbers(event);" onpaste = "return false" required>
                  </div>
                  <div class="form-group col-sm-3">
                    <label for="module">Type</label>
                   		<select class="form-control" id = "modulesforedit" name = "moduleID" required>
  						<c:forEach items="${modules}" var="elem">
  						<option value = "${elem.moduleID }" ${category.id == elem.moduleID ? 'selected' : '' }>${elem.name }</option>
  						</c:forEach>
                        </select>
                   
                  </div>
                  
                 
                 <div class="form-group">
                    <label>Description</label>
                     <textarea name = "description" id = "description" class="form-control" rows="3" required>${category.description }</textarea>
                 </div>
                  <div class="form-group">
                    <label>Image</label>
                    <input type = "text" name = "image" id = "image" value = "" readonly style = "display : none;">
                    <select id = "imagechoices">
                    	<option selected disabled>Select Icon</option>
                    	<option data-imagesrc="categories/coffee-category.png" value = "coffee-category.png"></option>
                    	<option data-imagesrc="categories/education-category.png" value = "education-category.png"></option>
                    	<option data-imagesrc="categories/electronics-category.png" value = "electronics-category.png"></option>
                    	<option data-imagesrc="categories/fitness-category.png" value = "fitness-category.png" ></option>
                    	<option data-imagesrc="categories/food-category.png" value = "food-category.png"></option>
                    	<option data-imagesrc="categories/health-category.png" value = "health-category.png"></option>
                    	<option data-imagesrc="categories/movie-category.png" value = "movie-category.png"></option>
                    	
                    	<option data-imagesrc="categories/shopping-category.png" value="shopping-category.png"></option>
                    	<option data-imagesrc="categories/transportation-category.png" value = "transportation-category.png"></option>
                    	
                    </select>
                 </div>
                <!--  <div class = "form-group">
                  	<input type = "file" name = "image">
                  </div> -->
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-submit">Submit</button>
                </div>
              </form>
            </div>
            <!-- /.card -->

</div>
<!-- <script src="adminlte/plugins/jquery/jquery.min.js">
 
  $("#category-form").submit(function(e){
	  
	  
	 e.preventDefault();
	 $.ajax({
		 type: "POST",
		 url: "createcategory.action",
		 data: $(this).serialize(),
		 success: function(data){
			 
				  window.location.href = "index.jsp";
			 },
			 }
		 }
	 	
	 })
 }); 
 </script> -->
 <script>
document.title = "Spendit - Category Edit";

</script>
</div>
<%@include file = "footer.jsp"%>