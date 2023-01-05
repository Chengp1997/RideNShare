console.log(location.port);
// const port = location.port === "8081" ? "8080" : location.port;
const port = 8888;
export const apiUrl =
  // "http://XXXXXXXX/api/";
  location.protocol +
  "//" +
  location.hostname +
  ":" +
  port +
  process.env.BASE_URL +
  "api/";
console.log(apiUrl);
