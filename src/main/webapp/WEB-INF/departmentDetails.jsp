<body>
    <div class="col-md-5 col-lg-4 order-md-last">
        <div class="card text-bg-info mb-3" style="max-width: 18rem;">
            <div class="card-header">Info</div>
                <div class="card-body">
                    <h5 class="card-title">Department info</h5>
                        <p class="card-text">Title: ${department.name}</p>
                        <p class="card-text">Location: ${department.location}</p>
                    <h5 class="card-title">User info</h5>
                        <p class="card-text">Username: ${pageContext.request.userPrincipal.name}</p>
                        <p class="card-text">Function: ${department.authUserFunction}</p>
                </div>
            </div>
        </div>
    </div>
</body>
