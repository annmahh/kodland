package ru.sfedu.kodland.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "container")
public class XMLContainer<T> {
    @ElementList(inline = true, required = false)
    private List<T> container;

    public XMLContainer() {
    }

    public XMLContainer(List<T> container) {
        this.container = container;
    }

    public List<T> getContainer() {
        return container;
    }

    public void setContainer(List<T> container) {
        this.container = container;
    }
}
