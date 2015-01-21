require_relative 'player'
require_relative 'oracle'
class SequentialPlayer <Player
  def initialize name, oracle,range
    super(name,oracle,range)
  end
  def guess
    super
    @guess = @range.begin
    @results = (@oracle.is_it @guess)
    @range = (@range.begin+1..@range.end)
  end
end
=begin
o = Oracle.new (0..100)
sq = SequentialPlayer.new 'Asma',o,(0..100)
begin
  sq.guess
  puts sq.to_s
end while sq.results!=0
puts sq.number_of_guesses
=end