package com.yotion.document;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * File is created by andreychernenko at 09.07.2025
 */

@Service
@Slf4j
public class DocumentService {
    public record Block(UUID id, String tag, String content, List<Block> blocks) {}

    public static void main(String[] args) {
        String html = "<p>Hello <b>World</b></p>";
        Element body = Jsoup.parse(html).body();

        List<Block> blocks = body
                .childNodes()
                .stream()
                .map(DocumentService::parseNode)
                .collect(Collectors.toList());
        StringBuilder logBuilder = new StringBuilder();
        buildLog(blocks, 0, logBuilder);
        log.info("\n{}", logBuilder);
    }

    private static Block parseNode(Node node) {
        if (node instanceof TextNode textNode) {
            return new Block(
                    UUID.randomUUID(),
                    "#text",
                    textNode.text(),
                    null
            );
        } else if (node instanceof Element element) {
            List<Block> childBlocks = element
                    .childNodes()
                    .stream()
                    .map(DocumentService::parseNode)
                    .collect(Collectors.toList());

            return new Block(
                    UUID.randomUUID(),
                    element.tagName(),
                    element.ownText(),
                    childBlocks.isEmpty() ? null : childBlocks
            );
        }
        return null;
    }

    private static void buildLog(List<Block> blocks, int indent, StringBuilder log) {
        if (blocks == null) return;
        for (Block block : blocks) {
            log.append(" ".repeat(indent * 2))
                    .append(block.tag())
                    .append(": \"")
                    .append(block.content())
                    .append("\"\n");
            buildLog(block.blocks(), indent + 1, log);
        }
    }
}
