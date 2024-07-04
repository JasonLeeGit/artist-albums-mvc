<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
 
<jsp:include page="fragments/header.jsp" />

<body>
 <script type="text/javascript">
	$(document).ready(function() {
		//https://getbootstrap.com/docs/4.0/components/tooltips/ 
	});
</script>

	<div class="container">

        <c:if test="${not empty msg}">
            <div class="alert alert-${alert} alert-dismissible fade show" role="alert">
                ${msg}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

		<h1>All Albums</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Artist Name</th>
					<th>Album Name</th>
					<th>Genre</th>
					<th>Format</th>
				</tr>
			</thead>

			<c:forEach var="albums" items="${albums}">
			    <tr>
				<td>${albums.id}</td>
				<td>${albums.artistName}</td>
				<td>${albums.albumName}</td>
				<td><a href="<c:url value="/albums/genre/${albums.genre}"/>" title="Search By Genre?">${albums.genre}</a></td>
				<td>${albums.format}</td>	
				<td>
				  <spring:url value="/albums/${albums.id}" var="albumUrl" />
				  <spring:url value="/albums/${albums.id}/delete" var="deleteUrl" />
				  <spring:url value="/albums/${albums.id}/update" var="updateUrl" />
				  <button class="btn btn-info" onclick="location.href='${albumUrl}'">Query</button>
				  <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
				  <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                </td>
			    </tr>
			</c:forEach>
		</table>

        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a class="btn btn-primary" href="/albums/add" role="button">Add Album</a>
        	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
        		<a class="btn btn-primary" href="/" role="button">Back To Home</a>
   		 	</div>
		</div>
		

	</div>

	<jsp:include page="fragments/footer.jsp" />
 
</body>
</html>