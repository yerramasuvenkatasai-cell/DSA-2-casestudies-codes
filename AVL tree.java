class RankNode{ 
int key; 
    int size=1; 
  
    RankNode left,right; 
  
    RankNode(int key){ 
        this.key=key; 
    } 
} 
  
static int size(RankNode n){ 
    return n==null ? 0 : n.size; 
} 
  
static void updateSize(RankNode n){ 
    if(n!=null) 
        n.size = 1 + size(n.left) + size(n.right); 
} 
  
static int rank_of(RankNode root,int key){ 
  
    int rank=1; 
  
    while(root!=null){ 
  
        if(key > root.key){ 
  
            root=root.left; 
  
        } 
  
        else if(key < root.key){ 
  
            rank += size(root.left)+1; 
  
            root=root.right; 
        } 
  
        else{ 
  
            rank += size(root.left); 
  
            return rank; 
        } 
    } 
  
    return -1; 
}