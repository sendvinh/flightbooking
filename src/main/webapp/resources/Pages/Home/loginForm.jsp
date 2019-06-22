<div id="login" class="col-md-3">
    <div class="col-md-11">
        <h2>Login</h2>
    </div>
    <form id="loginForm" class="col-md-11">
    <div class="form-group">
      <label for="username">Username:</label>
      <input type="username" class="form-control" id="username" placeholder="Username">
    </div>
    <div class="form-group">
      <label for="Password">Password</label>
      <input type="password" class="form-control" id="password" placeholder="Password">
    </div>
    <div class="form-check">
      <input type="checkbox" class="form-check-input" id="exampleCheck1">
      <label class="form-check-label" for="exampleCheck1">Check me out</label>
    </div>
    <div class="float-right">
    <button type="submit" class="btn btn-primary" onclick="document.getElementById('loginForm').submit()">Submit</button>
    </div>
    </form>
</div>