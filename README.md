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

`helm repo add bitnami https://charts.bitnami.com/bitnami`

`
helm upgrade my-kafka bitnami/kafka --set metrics.kafka.enabled=true --set replicaCount=3 --version 16.2.10
`
```yml
NAME: my-kafka
LAST DEPLOYED: Fri May 6 17:23:45 2022
NAMESPACE: default
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
CHART NAME: kafka
CHART VERSION: 16.2.10
APP VERSION: 3.1.0
** Please be patient while the chart is being deployed **
Kafka can be accessed by consumers via port 9092 on the following DNS name from within your cluster:
  my-kafka.default.svc.cluster.local
Each Kafka broker can be accessed by producers via port 9092 on the following DNS name(s) from within your cluster:
  my-kafka-0.my-kafka-headless.default.svc.cluster.local:9092
To create a pod that you can use as a Kafka client run the following commands:
  kubectl run my-kafka-client --restart='Never' --image docker.io/bitnami/kafka:3.1.0-debian-10-r89 --namespace default --command -- sleep infinity
  kubectl exec --tty -i my-kafka-client --namespace default -- bash
  PRODUCER:
    kafka-console-producer.sh \
      --broker-list my-kafka-0.my-kafka-headless.default.svc.cluster.local:9092 \
      --topic test
  CONSUMER:
    kafka-console-consumer.sh \
      --bootstrap-server my-kafka.default.svc.cluster.local:9092 \
      --topic test \
      --from-beginning
```
helm repo delete prometheus-community https://prometheus-community.github.io/helm-charts

helm delete prometheus-kafka-exporter \
--set kafkaServer[0]=my-kafka.default.svc.cluster.local:9092 \
prometheus-community/prometheus-kafka-exporter 

`helm install kafka-exporter \
--set replicaCount=1 \
--set replicaCount=my-kafka.default.svc.cluster.local:9092 \
gkarthiks/prometheus-kafka-exporter`

```yml
NAME: prometheus-kafka-exporter
LAST DEPLOYED: Fri May  6 20:03:51 2022
NAMESPACE: default
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
1. Get the application URL by running these commands:
  export POD_NAME=$(kubectl get pods --namespace default -l "app=prometheus-kafka-exporter,release=prometheus-kafka-exporter" -o jsonpath="{.items[0].metadata.name}")
  echo "Visit http://127.0.0.1:8080 to use your application"
  kubectl port-forward $POD_NAME 8080:80

```


# Run on k8s
`sbt docker:publish`
`kubectl apply -f job.yml`