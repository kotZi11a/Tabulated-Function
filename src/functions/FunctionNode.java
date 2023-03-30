package functions;

public class FunctionNode {
    public FunctionPoint data;
    FunctionNode next;
    FunctionNode prev;

    public  FunctionNode(){
        data = null;
        next = this;
        prev = this;
    }

    public boolean equals(Object o){
        if(!(o instanceof FunctionNode)) {
            return false;
        }
        FunctionNode temp = this.next;
        o = ((FunctionNode) o).next;
        while (temp != this) {
            if (!temp.data.equals(((FunctionNode) o).data)) {
                return false;
            }
            temp = temp.next;
            o = ((FunctionNode) o).next;
        }
        return true;
    }

    public int hashCode() {
        FunctionNode temp = this.next;
        int res = 0;
        while (temp != this) {
            res += temp.data.hashCode();
            temp = temp.next;
        }
        return res;
    }
}

