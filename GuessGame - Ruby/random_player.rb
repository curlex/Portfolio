require_relative 'player.rb'
class RandomPlayer < Player

  def initialize name, oracle,range
   super(name,oracle,range)
  end

  def guess
  super
    @guess = rand(@range.begin..@range.end)
    @results = (@oracle.is_it @guess)
    @results

  end



end


