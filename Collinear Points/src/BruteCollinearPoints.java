import java.util.Comparator;

public class BruteCollinearPoints {
   private int numberOfSegments = 0 ;
   private Point [][] seg; 
   private int segmentsSize; 
   private int segmentsIndex; 
	public BruteCollinearPoints(Point[] points) {
		if ( points == null ) throw new IllegalArgumentException ();
		for (Point p : points)if (p == null )throw new IllegalArgumentException ();
		seg = new Point[2][2];
		segmentsSize = 2;
		segmentsIndex = 0;
		Point origin;
		 double slope;
		    for (int i =0 ; i < points.length; i++ ) {
			   origin = points[i]; 
			   Comparator<Point> compareSlope = origin.slopeOrder();
			   for (int j = 0; j<points.length; j++) {
				   if (j==i) j++;
				   if (j >=points.length) break;
				   slope = origin.slopeTo(points[j] );
				   for (int k = 0; k< points.length; k++ ) {
					   while (k == i || k == j )  k++;
					   if (k >=points.length) break;
					   if (slope == origin.slopeTo(points[k])) {
						   for ( int l =0; l < points.length; l++) {
							   while (l==i || l==j || l == k)l++;
							   if (l >=points.length) break;
							   if (slope == origin.slopeTo(points[l])) {
								   if (origin.slopeTo(points[l]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException ();
								   numberOfSegments ++;
								   Point a = null;
								   Point b = null;
								   if ((origin.compareTo(points[j]) + origin.compareTo(points[k]) + origin.compareTo(points[l])) == 3) a = origin;
								   else if (origin.compareTo(points[j]) + origin.compareTo(points[k]) + origin.compareTo(points[l]) == -3) b = origin;
								   if (points[j].compareTo(origin) + points[j].compareTo(points[k]) + points[j].compareTo(points[l]) == 3) a = points[j];
								   else if (points[j].compareTo(origin) + points[j].compareTo(points[k]) + points[j].compareTo(points[l]) == -3) b = points[j];
								   if (points[k].compareTo(origin) + points[k].compareTo(points[j]) + points[k].compareTo(points[l]) == 3) a = points[k];
								   else if (points[k].compareTo(origin) + points[k].compareTo(points[j]) + points[k].compareTo(points[l]) == -3) b = points[k];
								   if (points[l].compareTo(origin) + points[l].compareTo(points[j]) + points[l].compareTo(points[k]) == 3) a = points[l];
								   else if (points[l].compareTo(origin) + points[l].compareTo(points[j]) + points[l].compareTo(points[k]) == -3) b = points[l];
								   boolean exists = false;
								   if (a != null && b != null) {
									   for (int z =0;z< segmentsIndex ; z++) {
										   if (a.equals(seg [0][z]) && b.equals(seg[1][z])){
											   exists = true;
											   break;}
										   }

									   if (!exists) {
									   seg [0][segmentsIndex] = a;
									   seg [1][segmentsIndex] = b;
									   segmentsIndex ++;
									   if (segmentsIndex >= segmentsSize/2 ) {
										   segmentsSize *= 2;
										   Point[][] newArray = new Point [2][segmentsSize];
										   for (int z = 0; z < 2; z++) {
									            final Point[] row = seg[z];
									            System.arraycopy(row, 0, newArray[z], 0, row.length);
									        }
										   seg = newArray;
										   newArray = null;
										   
									   }}
								   break;
								  }}
						   }
					   }
				   }
				   
			   }
		   }
 	}    
   
   public int numberOfSegments(){
	  return segmentsIndex ;
   }        
   
   public LineSegment[] segments() {
	 
	   LineSegment[] returned = new LineSegment [segmentsIndex];
	   for (int i = 0;i<segmentsIndex;i ++ )returned [i] = new LineSegment (seg[0][i],seg[1][i]);
	   return returned;
   }                


}