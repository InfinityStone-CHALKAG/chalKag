<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <chalKagTags:webCss/>
</head>
<body>

<chalKagTags:webHeader/>

<section class="not-found">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="code">
                    500
                </div>
                <h1>Internal Server Error</h1>
                <p class="lead">Something has happened and we are fixing it, come back later.</p>
                <div class="search-form">
                    <form>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" name="q" class="form-control" placeholder="Type something ...">
                                <div class="input-group-btn">
                                    <button class="btn btn-primary">Search</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="link">
                        or <a href="main">back to home</a>.
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<chalKagTags:webFooter/>

<chalKagTags:webJs/>

</body>
</html>
