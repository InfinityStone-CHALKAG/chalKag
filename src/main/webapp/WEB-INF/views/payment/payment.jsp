<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<!DOCTYPE html>
<head>

    <chalKagTags:webCss/>

</head>
<body>

<chalKagTags:webHeader/>

<div id="dataContainer" memberInfo='${memberInfo}'/>

<section class="page">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <h1 class="page-title">BOOST</h1>
                <p class="page-subtitle">BOOST YOUR ACCOUNT</p>
                <div class="line thin"></div>
                <div class="page-description">
                    <h4>Elevate Your Network with ACCOUNT BOOST – Upgrade to Premium for Just 9,900 KRW!</h4>
                    <br>
                    <p>
                        Are you looking to amplify your reach and connect with more professionals in your field? For
                        only 9,900 KRW, you can access our premium membership and the exclusive ACCOUNT BOOST feature,
                        ready to make your goals a reality! Plus, enjoy the flexibility of updating your identity on our
                        platform with Free Nickname Changes - because we understand the importance of evolving your
                        online presence.
                    </p>
                    <br>
                    <h4>Why Upgrade? Here’s What You’ll Gain</h4>
                    <br>
                    <h5>Enhanced Visibility</h5>
                    <p>
                        Say goodbye to being lost in the crowd. Our ACCOUNT BOOST propels your profile to the top,
                        ensuring your seen by a wider audience. This increased visibility means your work, ideas, and
                        profile don't just stay in the shadows but shine brightly for all to see.
                    </p>
                    <h5>Expect More Engagements</h5>
                    <p>
                        With your profile in prime position, getting noticed by peers, collaborators, and potential
                        clients becomes the new norm. More visibility leads to more interactions, and with it, more
                        opportunities to grow professionally and expand your network.
                    </p>
                    <h5>Maximize Your Opportunities</h5>
                    <p>
                        Why wait for opportunities to find you? Our premium members enjoy the forefront advantage,
                        driving more traffic to their profiles and posts. This means not just more views, but more
                        meaningful connections, conversations, and collaborations.
                    </p>
                    <h5>A Community Awaits</h5>
                    <p>Being premium doesn’t just mean being seen—it means being part of a vibrant community of
                        professionals who are serious about growth. Connect with like-minded individuals, share
                        insights, and build relationships that matter.
                    </p>
                    <h5>Flexibility to Reinvent</h5>
                    <p>
                        Change is the only constant, and your online persona should have the freedom to evolve. With
                        Free Nickname Changes, seamlessly update your profile as you grow in your professional journey,
                        ensuring your online presence is always aligned with your current aspirations and achievements.
                    </p>
                    <h4>Ready to Boost Your Account?</h4>
                    <p>
                        Upgrading for just 9,900 KRW is simple, and the potential benefits are immense. By investing in
                        your profile, you're not just boosting your visibility; you're opening doors to new
                        possibilities, connections, success, and the freedom to redefine your online identity as you see
                        fit.
                    </p>
                    <p>
                        Don't let your profile blend into the background. Make a move towards standing out and being
                        seen. Upgrade to premium today for just 9,900 KRW, activate your ACCOUNT BOOST, and embrace the
                        dynamic journey of your professional network flourishing like never before!
                    </p>
                    <div class="box-body">
                        <button class="btn btn-primary btn-block" onclick="requestPay()">boost</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<chalKagTags:webFooter/>

<!-- JS -->
<chalKagTags:webJs/>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="js/user/payment.js"></script>

</body>
</html>
