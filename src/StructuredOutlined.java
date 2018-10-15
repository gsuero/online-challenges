import java.io.*;
import java.util.*;
/**
 * Hopper structured oulined weighted
 * @author gsuero
 *
 */
public class StructuredOutlined {
   
    public static class Heading {
        protected int weight;
        protected String text;
        
        public Heading(int weight, String text) {
          this.weight = weight;
          this.text = text;
        }
      }
      public static class Node {
        protected Heading heading;
        protected List<Node> children;
        
        public Node(Heading heading) {
          this.heading = heading;
          this.children = new ArrayList<>();
        }
      }

      public static void main(String[] args) throws java.lang.Exception
      {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Heading> headings = new ArrayList<>();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
          headings.add(parse(line));
        }
//        headings.add(parse("H1 All about birds"));
//        headings.add(parse("H2 Kinds of Birds"));
//        headings.add(parse("H3 THE FINCH"));
//        headings.add(parse("H3 THE SWAN"));
//        headings.add(parse("H2 HABITATS"));
//        headings.add(parse("H3 WETLANDS"));
//        
//        headings.add(parse("H1 All about horse"));
//        headings.add(parse("H2 Kinds of horses"));
//        headings.add(parse("H3 THE horse1"));
//        headings.add(parse("H3 THE horse2"));
//        headings.add(parse("H2 HABITATS"));
//        headings.add(parse("H3 Desert"));
//        headings.add(parse("H3 Tropic"));
//        headings.add(parse("H2 HABITATS America"));
//        headings.add(parse("H3 Boston"));
//        headings.add(parse("H3 NYC"));
        
        Node outline = toOutline(headings);
        String html = toHtml(outline);
        System.out.println(html);
      }
      
      private static Node toOutline(List<Heading> headings) {
          // Implement this function. Sample code below builds an
          // outline of only the first heading.
          Node root = new Node(new Heading(0, ""));
          setChildren(headings, root, 0);
          return root;

      }
      
      private static void setChildren(List<Heading> headings, Node node, int position) {
          if (position >= headings.size()) {
              return;
          }
          Heading parent = headings.get(position);
          Node parentNode = new Node(parent);
          node.children.add(parentNode);
          
          int nextWeight = parent.weight + 1;
          
          for(int a = position + 1; a < headings.size(); a++) {
              Heading child = headings.get(a);
              Node nodeChild = new Node(child);
              if (child.weight == nextWeight) {
                  parentNode.children.add(nodeChild);
              } else if (child.weight < nextWeight) {
                  if (child.weight == 1 && (nextWeight - child.weight) == 1) {
                      parentNode = new Node(child);
                      node.children.add(parentNode);
                  } else {
                      break;
                  }
              } else if (child.weight > nextWeight) {
                  setChildren(headings, parentNode.children.get(parentNode.children.size() - 1), a); // children
              } 
                  
          }
      }

      /** Parses a line of input. 
          This implementation is correct for all predefined test cases. */
      private static Heading parse(String record) {
        String[] parts = record.split(" ", 2);
        int weight = Integer.parseInt(parts[0].substring(1));
        Heading heading = new Heading(weight, parts[1].trim());
        return heading;
      }
      
      /** Converts a node to HTML. 
          This implementation is correct for all predefined test cases. */
      private static String toHtml(Node node) {
        StringBuilder buf = new StringBuilder();
        if (!node.heading.text.isEmpty()) {
          buf.append(node.heading.text);
          buf.append("\n");
        }
        Iterator<Node> iter = node.children.iterator();
        if (iter.hasNext()) {
          buf.append("<ol>");
          
          while (iter.hasNext()) {
            Node child = iter.next();
            buf.append("<li>");
            buf.append(toHtml(child));
            buf.append("</li>");
            if (iter.hasNext()) {
              buf.append("\n");
            }
          }
          buf.append("</ol>");
        }
        return buf.toString();
      }
}
