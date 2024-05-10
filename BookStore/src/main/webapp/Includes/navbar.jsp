<% 
    String user = (String) request.getSession().getAttribute("username"); 
    if(user != null && user.equals("admin")) {
%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Book Store</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Dashboard.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">User Details</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Items.jsp">Items</a>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="LogoutServlet">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<% 
    } else {
%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Book Store</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Dashboard.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Cart.jsp">Cart</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Items.jsp">Items</a>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="LogoutServlet">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<% 
    }
%>
