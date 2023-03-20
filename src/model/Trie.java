package model;

class Trie {
    public boolean isword;
    public Trie next[];
    public Trie() {
        next = new Trie[26];
        isword = false;
    }

    public void insert(String word) {
        Trie cur = this;
        for(int i = 0;i<word.length();i++)
        {
            int index = word.charAt(i)-'a';
            if (cur.next[index]==null)
            {
                cur.next[index] = new Trie();
            }
            cur = cur.next[index];
        }
        cur.isword = true;
    }

    public boolean search(String word) {
        Trie cur = this;
        for(int i = 0;i<word.length();i++)
        {
            if (cur.next[word.charAt(i)-'a']==null)
            {
                return false;
            }
            else
            {
                cur = cur.next[word.charAt(i)-'a'];
            }
        }
        return cur.isword;
    }

    public boolean startsWith(String prefix) {
        Trie cur = this;
        for(int i = 0;i<prefix.length();i++)
        {
            if (cur.next[prefix.charAt(i)-'a']==null)
            {
                return false;
            }
            else
            {
                cur = cur.next[prefix.charAt(i)-'a'];
            }
        }
        return true;
    }
}

