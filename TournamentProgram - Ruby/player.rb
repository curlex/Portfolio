class Player
  attr_reader :results
  attr_accessor :rating
  include Comparable
  def initialize name,rating
    @name = name
    @rating = rating
    @results = []
  end
  def <=> player
    player.score <=>score
  end
  def add_result result
   result.each { |x| @results<<x.to_i}
  end
  def score
  @results.inject(0) {|score,results| score+=results}
  end
  def print_results
    @results.inject('') {|str,result| str+=result.to_s+" "}
  end
  def to_s
    "#{@name} \t#{@rating} \t#{print_results} \tTotal: #{score} points"
  end
end