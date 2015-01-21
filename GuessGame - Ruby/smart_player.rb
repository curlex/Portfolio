require_relative 'player.rb'
class SmartPlayer < Player

  def initialize name, oracle,range
    super(name,oracle,range)
  end

  def guess
    super
    @guess = ((@range.end - @range.begin)/2) + @range.begin
    @results = (@oracle.is_it @guess)
    if @results!=0
      binary_search @results
    end
    @results
  end

  def binary_search result
    case result
      when -1
        @range = (@range.begin..@guess-1)
      when 1
        @range = (@guess+1..@range.end)
    end
  end

  def to_s
    "Range: #{range.to_s} \n"+ super
  end
end
=begin
range = (0..100)
random_range = (range.begin..range.end)
oracle = Oracle.new random_range
s = SmartPlayer.new 'Asma', oracle ,random_range
s.play
=end

