enablePlugins(JavaAppPackaging, DockerPlugin)

dockerUsername := sys.props.get("docker.username")
dockerRepository := sys.props.get("docker.registry")

Docker / version := "with-prometheus-and-service-monitor"
Docker / organization := "miguelemos"
Docker / dockerBaseImage := "openjdk"
Docker / packageName := "miguelemos/alpakka_kafka_publisher"
Docker / dockerExposedPorts := Seq(9095)
