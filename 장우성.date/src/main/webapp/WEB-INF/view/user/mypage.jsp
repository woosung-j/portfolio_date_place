<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%> 
<head>
    <jsp:include page="../include/head.jsp"></jsp:include>
    <link rel="stylesheet" href="../../res/mobile.css" />
    <style>
        .circle-icon {
            background: whitesmoke;
            width: 100px;
            height: 100px;
            border-radius: 50%;
            text-align: center;
            line-height: 2.5rem;
            padding: 25px;
            margin-left: 1.5rem;
        }

        .bi-image {
            font-size: 40px;
        }

        .fixed-bottom .bi {
            font-size: 48px;
        }
    </style>
</head>
<body>
    <div class="container">
        <header class="mb-2">
            <nav class="row navbar bg-light text-center align-middle fixed-top">
                <h3 class="col font-gamja-flower">마이페이지</h3>
            </nav>
        </header>
        <div class="row pt-62 mt-5">
            <div class="col-1">
                <div class="circle-icon">
                    <i class="bi bi-image"></i>
                    <p class="img-font mt-4">홍길동</p>
                </div>
            </div>
            <div class="col-10 text-right">
                <div class="userEmail">ws@naver.com</div>
                <button type="button" class="btn btn-secondary btn-sm col-6 mt-3 ml-0" data-target="#fileUpload" data-toggle="modal">프로필 사진 업로드</button>
                <a href="../User/07.html">
                    <button type="button" class="btn btn-secondary btn-sm col-6 mt-3">회원 프로필 수정</button>
                </a>
            </div>
        </div>
        <hr class="mt-4" />
        <div class="row-1 pb-5 mb-3">
            <a href="../place/06.html">
                <button type="button" class="btn btn-primary btn-lg col-12 mt-3">찜</button>
            </a>
            <a href="../region/01.html">
                <button type="button" class="btn btn-primary btn-lg col-12 mt-3">관심 지역</button>
            </a>
            <a href="../review/04.html">
                <button type="button" class="btn btn-primary btn-lg col-12 mt-3">리뷰 내역</button>
            </a>
            <a href="../community/06.html">
                <button type="button" class="btn btn-primary btn-lg col-12 mt-3">피드 내역</button>
            </a>
            <a href="../declaration/01.html">
                <button type="button" class="btn btn-primary btn-lg col-12 mt-3">신고 내역</button>
            </a>
            <a href="logout">
                <button type="button" class="btn btn-primary btn-lg col-12 mt-3">로그 아웃</button>
            </a>
        </div>
        <div class="navbar">
            <ul class="navbar nav-item bg-light fixed-bottom mb-0 list-style-none">
                <li>
                    <a href="/" class="btn w-auto" type="button">
                        <i class="icon bi-house-door-fill fa-3x"></i>
                    </a>
                </li>
                <li>
                    <a href="../community/01.html" class="btn w-auto" type="button">
                        <i class="icon bi-file-earmark-text fa-3x"></i>
                    </a>
                </li>
                <li>
                    <a href="../place/05.html" class="btn w-auto" type="button">
                        <i class="icon bi-map fa-3x"></i>
                    </a>
                </li>
                <li>
                    <a href="../place/06.html" class="btn w-auto" type="button">
                        <i class="icon bi-heart fa-3x"></i>
                    </a>
                </li>
                <li>
                    <a href="mypage" class="btn w-auto" type="button">
                        <i class="icon bi-person-circle fa-3x"></i>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="modal fade" id="fileUpload" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body text-center py-5">
                    <form method="post" enctype="form-data">
                        <label>
                            <input type="file" name="userFace" />
                        </label>
                        <br />
                        <a href="#" type="submit" class="btn btn-info btn-lg col-12" data-dismiss="modal"> 확인 </a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
