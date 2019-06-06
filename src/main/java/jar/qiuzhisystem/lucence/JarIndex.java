package jar.qiuzhisystem.lucence;
/**
 * jar包索引类
 * @author 12952
 *
 */

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import jar.qiuzhisystem.entity.Jar;
import jar.qiuzhisystem.utils.StringUtil;

public class JarIndex {

	private Directory dir = null;
	private final static String LUCENE_PATH = "c://lucene";
	/**
	 * 获取indexWriter实例
	 * @return
	 * @throws Exception
	 */
	private IndexWriter getWriter() throws Exception{
		dir = FSDirectory.open(Paths.get(LUCENE_PATH));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir, iwc);
		return writer;
	}
	/**
	 * 添加jar包索引
	 * @param jar
	 * @throws Exception
	 */
	public  void addIndex(Jar jar) throws Exception{
		IndexWriter writer = getWriter();
		Document document = new Document();
		document.add(new StringField("id", jar.getUuid(), Field.Store.YES));
		document.add(new TextField("name", jar.getName().replaceAll("-", ""), Field.Store.YES));
		writer.addDocument(document);
		writer.close();
	}
	/**
	 * 修改jar包索引
	 * @param jar
	 * @throws Exception
	 */
	public void updateIndex(Jar jar) throws Exception{
		IndexWriter writer = getWriter();
		Document document = new Document();
		document.add(new StringField("id", jar.getUuid(), Field.Store.YES));
		document.add(new TextField("name", jar.getName().replaceAll("-", ""), Field.Store.YES));
		writer.updateDocument(new Term("id", jar.getUuid()), document);
		writer.close();
	}
	/**
	 * 删除jar包索引
	 * @param jarId
	 * @throws Exception
	 */
	public void deleteIndex(String jarId) throws Exception{
		IndexWriter writer = getWriter();
		writer.deleteDocuments(new Term("id", jarId));
		writer.forceMergeDeletes();//强制删除
		writer.commit();
		writer.close();
	}
	/**
	 * 查询jar包信息
	 * @param q
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public List<Jar> searchJar(String q, int n) throws Exception{
		dir = FSDirectory.open(Paths.get(LUCENE_PATH));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher is = new IndexSearcher(reader);
		//解析器
		//标准分词
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = new QueryParser("name", analyzer);
		Query query = parser.parse(q);
		
		TopDocs hits = is.search(query, n);//查询n条
		QueryScorer scorer = new QueryScorer(query);
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","</b></font>");
		//设置高亮
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
		highlighter.setTextFragmenter(fragmenter);
		List<Jar> jarList = new LinkedList<Jar>();
		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			//获取doc文档
			Document doc = is.doc(scoreDoc.doc);
			Jar jar = new Jar();
			jar.setUuid(doc.get("id"));
			String name = doc.get("name");
			jar.setNoTagName(name);
			if(name != null) {
				TokenStream tokenStream = analyzer.tokenStream("name", new StringReader(name));
				String hName = highlighter.getBestFragment(tokenStream, name);
				if(StringUtil.isEmpty(hName)) {
					jar.setName(name);
				}else {
					jar.setName(hName);
				}
			}
			jarList.add(jar);
		}
		return jarList;
	}
//	public static void main(String[] args) throws Exception {
//		List<Jar> jarList=new JarIndex().searchJar("api", 10);
//		for(Jar jar:jarList){
//			System.out.println(jar);
//		}
//	}
}
