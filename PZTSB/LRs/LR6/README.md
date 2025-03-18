# Building new version of the app

```bash
docker build -t makipavelsu/planes-backend:x.y.z .
docker push makipavelsu/planes-backend:x.y.z
helm upgrade test-2 .\helm\
```