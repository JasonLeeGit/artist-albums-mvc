<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${alert} alert-dismissible fade show" role="alert">
			${msg}
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
	</c:if>

	<h1>Album Information</h1>

    <div class="row">
        <label for="staticID" class="col-sm-2 col-form-label">ID</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control-plaintext" id="staticID" value="${album.id}">
        </div>
    </div>

    <div class="row">
        <label for="staticArtistName" class="col-sm-2 col-form-label">Artist Name</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control-plaintext" id="staticArtistName" value="${album.artistName}">
        </div>
    </div>

    <div class="row">
        <label for="staticAlbumName" class="col-sm-2 col-form-label">Album Name</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control-plaintext" id="staticAlbumName" value="${album.albumName}">
        </div>
    </div>

    <div class="row">
        <label for="staticGenre" class="col-sm-2 col-form-label">Genre</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control-plaintext" id="staticGenre" value="${album.genre}">
        </div>
    </div>
    
    <div class="row">
        <label for="staticFormat" class="col-sm-2 col-form-label">Format</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control-plaintext" id="staticFormat" value="${album.format}">
        </div>
    </div>

    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <a class="btn btn-primary" href="/" role="button">Back To Home</a>
    </div>

</div>

<jsp:include page="fragments/footer.jsp" />

</body>
</html>