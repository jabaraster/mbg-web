<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Upload</title>
</head>
<body>

  <h1>${message}</h1>

  <form action="" method="post" enctype="multipart/form-data">
    <ul>
      <li><input type="text" name="text" /></li>
      <li><input type="file" name="file" /></li>
      <li><input type="submit" /></li>
    </ul>
  </form>

</body>
</html>