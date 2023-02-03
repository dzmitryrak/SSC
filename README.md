<h1 align="center">Selenide Salesforce Core</h1>

<div align="center">

**Ready-to-use core for salesforce based test automation projects**

[![Maven Central](https://img.shields.io/maven-central/v/io.github.dzmitryrak/ssc.svg)](https://central.sonatype.dev/artifact/io.github.dzmitryrak/ssc/0.1.0/overview)
[![Build Status](https://github.com/dzmitryrak/SSC/actions/workflows/bvt.yml/badge.svg)](https://github.com/dzmitryrak/SSC/actions/workflows/bvt.yml)
[![Apache-2.0 License](https://img.shields.io/badge/License-Apache--2.0-informational.svg)](https://choosealicense.com/licenses/apache-2.0/)

</div>

## What is SSC?

SSC – generic core for speeding up your own Salesforce test automation framework development with [Selenide](https://selenide.org/) under the hood.

## Usage

<div align="center">

### Interaction with objects 

</div>

  ###### Create object data template

    Map<String, String> account = new HashMap<>() {{
        put("Account Name", "Erica's account");
        put("Parent Account", "Erica Larson");
        put("Type", "Prospect");
        ...
    }};

  ###### Fill object modal form

    listView
        .open("Account")
        .clickNew()
        .enterData(account)
        .save()
        .waitTillModalClosed()
        .waitTillOpened();

  ###### Validate object data

    detailsPage
        .clickTab("Details")
        .validate(account);

<div align="center">

### More to come...

</div>

## Contributing

If you are about to contribute, please, study our [release strategy](https://github.com/dzmitryrak/SSC/wiki/Release-guide) first. It is planned to cover much more Salesforce functionalities in the upcoming future, we would be thankful for your ideas on SSC improvement.

## License

SSC is an open-source project, and distributed under the [Apache-2.0](https://choosealicense.com/licenses/apache-2.0/) license.
