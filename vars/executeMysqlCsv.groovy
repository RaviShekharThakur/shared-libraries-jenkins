def call(Map config = [:]) {

    if (!config.csvFile?.trim()) {
        error "CSV Base64 missing"
    }

    if (!config.clusterName) {
        error "Cluster name is required"
    }

    if (!config.schemaName) {
        error "Schema name is required"
    }

    if (!config.env) {
        error "Environment is required"
    }

    stage('Decode CSV') {

        echo "Environment: ${config.env}"
        echo "Cluster: ${config.clusterName}"
        echo "Schema: ${config.schemaName}"

        writeFile file: "input.b64", text: config.csvFile

        sh """
            cat input.b64 | base64 -d > uploaded.csv
        """

        echo "✔ CSV decoded successfully"
    }
}

