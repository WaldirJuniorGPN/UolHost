package br.com.WaldirJunior.UolHost.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class CodinomeService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    private List<String> listaCodinomeVingadores = new ArrayList<>();
    private List<String> listaCodinomeLigaDaJustica = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void lerJsonData() {

        try {
            String codinomeResponse = this.restTemplate.getForObject(env.getProperty("vingadores"), String.class);
            JsonNode jsonNode = objectMapper.readTree(codinomeResponse);
            ArrayNode vingadores = (ArrayNode) jsonNode.get("vingadores");

            for (JsonNode item : vingadores) {
                this.listaCodinomeVingadores.add(item.get("codinome").asText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void lerXmlData() {
        try {
            String ligaDaJusticaResponse = this.restTemplate.getForObject(env.getProperty("liga.da.justica"), String.class);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(ligaDaJusticaResponse)));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("codinome");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    this.listaCodinomeLigaDaJustica.add(element.getElementsByTagName("codinome").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
