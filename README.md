### Genes Java Project

  A simplified application for biologists in JavaFX, which helps them study genes. Each Gene has a name, organism, function and an associated sequence. The gene sequence is composed only of the letters {'A', 'C', 'G', 'T'}, which represent the nucleotides. We have the following 5 items in our connected database:
     
      yqgE | E_Coli_K12 | facilitates stress-induced mutagenesis | ATGAATTTACAGCAT
      ppiA | M_tuberculosis | is thought to facilitiate proper protein folding | TTTCATCACCGTCGG
      Col2a1 | Human | helps in making one component of collagen | TTAAAGCATGGCTCTGTG
      TP53 | Human | codes for a tumor suppressor protein | CTCAAAAGTCTAGAGCCACCGTC
      hokC | E_Coli_K12 | synthetizes a host killing protein | TTAATGAAGCATAAGCTTGATTTC
    
  We have the following functionalities: 
  
    1. Visualize all genes in a list (name, organism, function, sequence), filtered by organism. When the application starts, the list is populated automatically.
    2. Ability to search genes by organism and text (combo box containing organisms, text field for text), showing only the genes belonging to that organism and whose name or function match the text (filtered using Java Streams).
    3. When a gene is clicked in the list, its function and sequence are displayed in two other controls (text field / area). The user can update the function and/or sequence and the modifications are saved in the database.
    4. The user can identify the point mutation (a point mutation is a single nucleotide that is different between two sequences). The user selects a gene from the list and enters a sequence in a text field. The application will show all point mutations (all differences between the selected gene's sequence and the input sequence) as follows: position in the gene, mutation. If there are no mutations, the application will show a message.
