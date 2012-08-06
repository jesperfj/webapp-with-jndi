# Test Web App for Tomcat Build Pack

This web app is a simple test web app for testing this [Tomcat buildpack](https://github.com/jesperfj/buildpack-tomcat).

# Build

```
$ mvn package
```

# Deploy to Heroku

See [Tomcat buildpack](https://github.com/jesperfj/buildpack-tomcat) for full instructions. You'll need a Heroku app with a database provisioned and promoted to `DATABASE_URL` and you'll need the [anvil](https://github.com/ddollar/heroku-anvil) heroku CLI plugin:

```
$ heroku build target/my-webapp -r -b http://github.com/jesperfj/buildpack-tomcat.git -a myapp
```

# Test

```
$ curl http://stormy-reef-2997.herokuapp.com/hello
```

should return `Hello World`.

```
$ heroku logs -a stormy-reef-2997
```

You should see this log line:

```
2012-08-06T16:28:48+00:00 app[web.1]: Detected DATABASE_URL. Configuring Java System properties: jdbc.url=...
```

which indicates that `DATABASE_URL` environment variable was read and parse correctly.

You should see:

```
2012-08-06T16:29:50+00:00 app[web.1]: INFO: SQL Connection Test: true
```

which indicates that the servlet connected successfully to the database.
