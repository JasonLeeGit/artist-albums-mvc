<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/header.jsp" />

<div class="container">

	<c:choose>
		<c:when test="${albumForm['new']}">
			<h1>Add Album</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Album</h1>
		</c:otherwise>
	</c:choose>

	<form:form method="post" modelAttribute="albumForm" action="/albums">

		<form:hidden path="id" />

        <spring:bind path="artistName">
            <div class="mb-3 row">
                <label for="artistName" class="col-sm-2 col-form-label">Artist Name</label>
                <div class="col-sm-10">
                    <form:input path="artistName" type="text" id="artistName"
                        class="form-control ${status.error ? 'is-invalid' : ''}"
                        aria-describedby="validationNameFeedback" />
                    <form:errors path="artistName" id="validationArtistNameFeedBack" class="invalid-feedback" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="albumName">
            <div class="mb-3 row">
                <label for="albumName" class="col-sm-2 col-form-label">Album Name</label>
                <div class="col-sm-10">
                    <form:input path="albumName" type="text" id="albumName"
                        class="form-control ${status.error ? 'is-invalid' : ''}"
                        aria-describedby="validationEmailFeedback" />
                    <form:errors path="albumName" id="validationAlbumNameFeedback" class="invalid-feedback" />
                </div>
            </div>
        </spring:bind>
            
        <spring:bind path="genre">
		  <div class="mb-3 row">
			<label for="genre" class="col-sm-2 col-form-label">Genre</label>
			<div class="col-sm-10">
				<form:select path="genre" id="genre" class="form-select ${status.error ? 'is-invalid' : ''}"
				    aria-describedby="validationGenreFeedback" >
					<form:option value="NONE" label="-- Select --" />
					<form:options items="${genreList}" />
				</form:select>
				<form:errors path="genre" id="validationGenreFeedback" class="invalid-feedback" />
			</div>
		  </div>
		</spring:bind>

        <spring:bind path="format">
            <div class="mb-3 row">
                <label for="format" class="col-sm-2 col-form-label">Format</label>
                <div class="col-sm-10">
                    <form:checkboxes path="format" class="form-check-input ${status.error ? 'is-invalid' : ''}"
                        items="${formatList}" element="div class='form-check form-check-inline'"
                        aria-describedby="validationFormatFeedback" />
                    <form:errors path="format" id="validationFormatFeedback"
                        class="invalid-feedback-force-display" element="div" />
                </div>
			</div>
		</spring:bind>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<c:choose>
				<c:when test="${albumForm['new']}">
			    	<button type="submit" class="btn btn-primary btn">Add</button>
			       		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
        					<a class="btn btn-primary" href="/" role="button">Back To Home</a>
   					 	</div>
			  	</c:when>
				<c:otherwise>
					<button type="submit" class="btn btn-primary btn">Update</button>
			  			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
        					<a class="btn btn-primary" href="/" role="button">Back To Home</a>
   		 				</div>
				</c:otherwise>
			</c:choose>
		</div>

	</form:form>

</div>

<jsp:include page="fragments/footer.jsp" />

</body>
</html>