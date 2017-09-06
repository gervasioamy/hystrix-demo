# Hystrix demo
Simple PoC to test how to integrate hystrix with Spring Cloud support in just a few steps

### hystrix-demo-client
This is (tiny) server which consumes a RESTFul service using `RestTemplate` provided by hystrix-demo-server.
It exposes another RESTFul services which does that call:
```
curl -X GET 'http://localhost:9090/users/{anyId}?fallback=true&circuitBraker=true'
```
There are two url parameters:
* _fallback_ `(true | false)`: to run the command with fallback configured
* _circuitBraker_ `(true | false)`: to enable or not hystrix. Useful to test behaviour with or without hystrix

### hystrix-demo-server
This is (tiny) server which exposes a simple RESTFul service
```
curl -X GET 'http://localhost:9090/users/{anyId}
```


### hystrix-demo-dashboard
Another applications which enables the hystrix dashboard to monitor 
To start it, run this app and go to `http://localhost:9092/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A9090%2Fhystrix.stream`


### Useful links:
* https://spring.io/guides/gs/circuit-breaker/
