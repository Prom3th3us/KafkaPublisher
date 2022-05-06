# KafkaPublisher
In this repository we will publish as many messages as we can to Kafka, because we will observe the results in Grafana and make a report

# Run local
`docker-compose up -d`

`sbt run `

`kcat -b 0.0.0.0:9092 -C -t benchmark`

```
user-999992: so long time user-999992
user-999993: Hello Mr. user-999993
user-999994: Do you want to buy a boat? user-999994
user-999995: hello user-999995
user-999996: nice to meet you user-999996
user-999997: so long time user-999997
user-999998: Hello Mr. user-999998
user-999999: Do you want to buy a boat? user-999999


```

# Run on k8s
`sbt docker:publish`
`kubectl apply -f job.yml`