
<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** This readme file is created using the Best-README-Template
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]




<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/RWS-CFNS/backend_konijn">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Backend System for collecting data from measuringboxes on the North Sea and generating a heatmap </h3>

  <p align="center">
    Part of the project interconnectivity on the North Sea. Part of the CFNS organisation
    <br />
    <a href="https://github.com/RWS-CFNS/backend_konijn"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/RWS-CFNS/backend_konijn">View Demo</a>
    ·
    <a href="https://github.com/RWS-CFNS/backend_konijn/issues">Report Bug</a>
    ·
    <a href="https://github.com/RWS-CFNS/backend_konijn/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project


<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With
[![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)](Eclipse-url)
[![Spring Boot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white`)](Spring-url)
[![RabbitMQ](https://img.shields.io/badge/rabbitmq-%23FF6600.svg?&style=for-the-badge&logo=rabbitmq&logoColor=white`)](RabbitMQ-url)
[![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](java-url)
[![Lombok](projectlombok.org-image-url)](lombok-url)


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

You will need a text editor or IDE to run the program. Lombok needs to be installed seperately. By default, Eclipse was used to make this project.

### Prerequisites
The back-end only works if a rabbitmq server is running on this device and is properly configured. Run the batch file in the main folder of this github repo if that is not the case
warning: this may change settings on an existing rabbitmq server.

### Installation
Clone the repo
   ```sh
   git clone https://github.com/RWS-CFNS/backend_konijn.git
   ```


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

important: to run the program, open the nl.cfns.boot package. run the class named "BackendKonijnApplication"

for the rabbitMQ management console, go to http://127.0.0.1:15672/#/
for the H2 databse console, go to http://localhost:8090/h2-console/
for our github repository, go to https://github.com/RWS-CFNS/backend_konijn
for a heatmap datastring that can be used by a front-end application, access localhost:8090/heatmap/data

<p align="right">(<a href="#readme-top">back to top</a>)</p>




<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the GPL-3.0 License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Niels de Bruin - niels.de.bruin@rws.nl

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [README template](https://github.com/othneildrew/Best-README-Template)
* []()
* []()

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/RWS-CFNS/backend_konijn.svg?style=for-the-badge
[contributors-url]: https://github.com/RWS-CFNS/backend_konijn/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/RWS-CFNS/backend_konijn.svg?style=for-the-badge
[forks-url]: https://github.com/RWS-CFNS/backend_konijn/network/members
[stars-shield]: https://img.shields.io/github/stars/RWS-CFNS/backend_konijn.svg?style=for-the-badge
[stars-url]: https://github.com/RWS-CFNS/backend_konijn/stargazers
[issues-shield]: https://img.shields.io/github/issues/RWS-CFNS/backend_konijn.svg?style=for-the-badge
[issues-url]: https://github.com/RWS-CFNS/backend_konijn/issues
[license-shield]: https://img.shields.io/github/license/RWS-CFNS/backend_konijn.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[Eclipse-url]:https://www.eclipse.org/downloads/
[Spring-url]:https://spring.io/projects/spring-boot
[rabbit-url]:https://www.rabbitmq.com/
[lombok-url]:https://projectlombok.org/
[java-url]:https://openjdk.org/
